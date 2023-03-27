package Jwindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class wd {
    public static void main(String[] args) {
        JFrame window_first = new JFrame("初始窗口");

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("文件");
        JMenuItem openMenuItem = new JMenuItem("打开");
        JMenuItem saveMenuItem = new JMenuItem("保存");
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);

        JMenu editMenu = new JMenu("编辑");
        JMenuItem cutMenuItem = new JMenuItem("剪切");
        JMenuItem copyMenuItem = new JMenuItem("复制");
        JMenuItem pasteMenuItem = new JMenuItem("粘贴");
        editMenu.add(cutMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        openMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("您单击了打开菜单项。");
            }
        });

        saveMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("您单击了保存菜单项。");
            }
        });

        cutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("您单击了剪切菜单项。");
            }
        });

        copyMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("您单击了复制菜单项。");
            }
        });

        pasteMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("您单击了粘贴菜单项。");
            }
        });

        window_first.setJMenuBar(menuBar);

        window_first.setVisible(true);//设置窗口为可见，默认为不可见
        Container con1 = window_first.getContentPane(); //获取窗口上的容器
        //con1.setBackground(Color.black); //设置窗口的背景颜色为黑色
        window_first.setBounds(100,100,400,400); //设置窗口距离左上角100*100的距离，大小为400*400



    }
}
