package task_1;

import java.util.*;


// Реализовать управление роботом с помощью команд консоли
public class RobotGameMain {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Приветствуем пользователя и объясняем, куда он попал

        System.out.println("Введите размеры карты:");
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        final RobotMap map = new RobotMap(n, m);
        System.out.println("Карта успешно создана");

        final CommandManager manager = new CommandManager(map);
        while (true) {
            System.out.println("""
                    Доступные действия:
                    1. Для создания робота введите create x y, где x и y - координаты для нового робота
                    2. Для вывода списка всех созданных роботов, введите list
                    3. Для перемещения робота введите move id, где id - идентификатор робота
                    4. Для изменения направления введите changedir id DIRECTION, где id - идентификатор робота, DIRECTION - одно из значений {TOP, RIGHT, BOTTOM, LEFT}
                    5. Для удаления робота введите delete id, где id - идентификатор робота
                    6. Для выхода напишите exit
                    ... список будет пополняться
                    """);

//            testDrive(manager);

            manager.acceptCommand("changedir 1 ");

            String command = sc.nextLine();
            manager.acceptCommand(command);
        }
    }

    public static void testDrive(CommandManager manager) {
        manager.acceptCommand("create 5 5");
        manager.acceptCommand("create 2 2");
        manager.acceptCommand("list");

        manager.acceptCommand("changedir 1 af");
        manager.acceptCommand("changedir 1 TOP");

        manager.acceptCommand("delete 2");
        manager.acceptCommand("exit");

        System.exit(0);
    }

    private static class CommandManager {

        private final RobotMap map;
        private final List<CommandHandler> handlers;

        public CommandManager(RobotMap map) {
            this.map = map;
            handlers = new ArrayList<>();
            initHandlers();
        }

        private void initHandlers() {
            initCreateCommandHandler();
            initListCommandHandler();
            initMoveCommandHandler();
            initChangeDIRCommandHandler();
            initDeleteRobotCommandHandler();
            initExitCommandHandler();
        }

        private void initExitCommandHandler() {
            handlers.add(new CommandHandler() {
                @Override
                public String name() {
                    return "exit";
                }

                @Override
                public void runCommand(String[] args) throws Exception {
                    System.exit(0);
                }
            });
        }

        private void initDeleteRobotCommandHandler() {
            handlers.add(new CommandHandler() {
                @Override
                public String name() {
                    return "delete";
                }

                @Override
                public void runCommand(String[] args) throws Exception {
                    Optional<Long> id = Optional.ofNullable(Long.valueOf(args[0]));
                    boolean success = map.remove(id
                            .orElseThrow(() -> new IllegalArgumentException("Непривильно введен id" + args[0]
                                    + " требуется Long"))
                    );

                    if (success) System.out.println("Робот удален " + args[0]);
                    if (!success) throw new NoSuchElementException("Робот остутствует в системе" + args[0]);
                }
            });
        }


        private void initChangeDIRCommandHandler() {
            handlers.add(new CommandHandler() {
                @Override
                public String name() {
                    return "changedir";
                }

                @Override
                public void runCommand(String[] args) throws Exception {
                    Direction dir = Direction.ofString(args[1]);
                    if (dir == null) throw new IllegalArgumentException("Неправильно введено направление " + args[1]);

                    Optional<Optional<RobotMap.Robot>> robot = Optional.ofNullable(map.getById(Long.valueOf(args[0])));
                    robot.get()
                            .orElseThrow(() -> new NoSuchElementException("Робот" + args[0] + " остутствует в системе"))
                            .changeDirection(dir);
                    System.out.println("Направление робота " + robot.get().get() + " измененно на " + dir);
                }
            });
        }

        //System.out.println("Робот с идентификатором " + args[0] + " не найден")
        private void initCreateCommandHandler() {
            handlers.add(new CommandHandler() {
                @Override
                public String name() {
                    return "create";
                }

                @Override
                public void runCommand(String[] args) {
                    int x = Integer.parseInt(args[0]);
                    int y = Integer.parseInt(args[1]);
                    RobotMap.Robot robot = map.createRobot(new Point(x, y));
                    System.out.println("Робот " + robot + " успешно создан");
                }
            });
        }

        private void initListCommandHandler() {
            handlers.add(new CommandHandler() {
                @Override
                public String name() {
                    return "list";
                }

                @Override
                public void runCommand(String[] args) {
                    map.acceptRobots(System.out::println);
                }
            });
        }

        private void initMoveCommandHandler() {
            handlers.add(new CommandHandler() {
                @Override
                public String name() {
                    return "move";
                }

                @Override
                public void runCommand(String[] args) {
                    Long robotId = Long.parseLong(args[0]);
                    Optional<RobotMap.Robot> robot = map.getById(robotId);
                    robot.ifPresentOrElse(RobotMap.Robot::move,
                            () -> System.out.println("Робот с идентификатором " + robotId + " не найден"));
                }
            });
        }

        public void acceptCommand(String command) {
            String[] split = command.split(" ");
            String commandName = split[0];
            String[] commandArgs = Arrays.copyOfRange(split, 1, split.length);

            for (CommandHandler handler : handlers) {
                if (!commandName.equals(handler.name())) continue;
                try {
                    handler.runCommand(commandArgs);
                    return;
                } catch (Exception e) {
                    System.err.println("Во время обработки команды \"" + commandName + "\" произошла ошибка: " + e.getMessage());
                    return;
                }
            }
            System.out.println("Команда не найдена");
        }

        private interface CommandHandler {
            String name();

            void runCommand(String[] args) throws Exception;
        }
    }

}
