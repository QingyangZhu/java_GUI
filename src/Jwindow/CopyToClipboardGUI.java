package Jwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;

public class CopyToClipboardGUI extends JFrame {
    private JLabel label;
    private JTextField textField;
    private JButton button;

    public CopyToClipboardGUI() {
        // 设置窗口标题
        setTitle("复制到剪贴板");

        // 创建UI元素
        label = new JLabel("要复制的文本：");
        textField = new JTextField(20);
        button = new JButton("复制");

        // 添加事件处理器
        button.addActionListener(e -> {
            StringSelection stringSelection = new StringSelection(textField.getText());
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            JOptionPane.showMessageDialog(this, "复制成功！");
        });

        // 使用布局管理器将UI元素添加到窗口中
        setLayout(new FlowLayout());
        add(label);
        add(textField);
        add(button);

        // 设置窗口大小和位置
        setSize(300, 100);
        setLocationRelativeTo(null);

        // 显示窗口
        setVisible(true);
    }

    public static void main(String[] args) {
        new CopyToClipboardGUI();
    }
}
