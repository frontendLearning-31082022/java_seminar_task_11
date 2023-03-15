package patterns.MVP;

import javax.swing.*;

public interface View {

    void setDisplayText(String data);
    String getDisplayText();

    void setInputTextArea(String data);
    String getInputTextArea();
}
