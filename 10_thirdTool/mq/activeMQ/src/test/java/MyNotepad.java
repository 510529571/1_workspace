
import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class MyNotepad extends JFrame implements ActionListener, ItemListener {
	boolean haveCreated = false;
	File file = null;
	String strtext = "";
	int findIndex = 0;
	String findStr;

	JMenuBar menubar = new JMenuBar();
	JMenu meFile = new JMenu(" 文件 ");
	JMenu meEdit = new JMenu(" 编辑 ");
	JMenu meStyle = new JMenu(" 风格 ");
	JMenu meHelp = new JMenu(" 帮助 ");

	JMenuItem miCreate = new JMenuItem(" 新建 ");
	JMenuItem miOpen = new JMenuItem(" 打开 ");
	JMenuItem miSave = new JMenuItem(" 保存 ");
	JMenuItem miSaveAs = new JMenuItem(" 另存为 ");
	JMenuItem miExit = new JMenuItem(" 退出 ");

	JMenuItem miUndo = new JMenuItem(" 撤消 ");
	JMenuItem miCut = new JMenuItem(" 剪切 ");
	JMenuItem miCopy = new JMenuItem(" 复制 ");
	JMenuItem miPaste = new JMenuItem(" 粘贴 ");
	JMenuItem miDelete = new JMenuItem(" 删除 ");
	JMenuItem miFind = new JMenuItem(" 查找 ");
	JMenuItem miNext = new JMenuItem(" 查找下一个 ");
	JMenuItem miReplace = new JMenuItem(" 替换 ");

	// 右键弹出菜单项
	JMenuItem pmUndo = new JMenuItem(" 撤消 ");
	JMenuItem pmCut = new JMenuItem(" 剪切 ");
	JMenuItem pmCopy = new JMenuItem(" 复制 ");
	JMenuItem pmPaste = new JMenuItem(" 粘贴 ");
	JMenuItem pmDelete = new JMenuItem(" 删除 ");

	JCheckBoxMenuItem miNewLine = new JCheckBoxMenuItem(" 自动换行 ");
	JMenu smLookFeel = new JMenu(" 观感 ");
	JMenuItem metal = new JMenuItem(" Metal ");
	JMenuItem motif = new JMenuItem(" Motif ");
	JMenuItem windows = new JMenuItem(" Windows ");

	JMenuItem miAbout = new JMenuItem(" 关于 ");

	JPopupMenu popupMenu;
	JTextArea text = new JTextArea();

	public MyNotepad() {
		super(" 我的记事本 ");
		// 为便于区分事件源，设定名字
		miCreate.setActionCommand(" create ");
		miOpen.setActionCommand(" open ");
		miSave.setActionCommand(" save ");
		miSaveAs.setActionCommand(" saveAs ");
		miExit.setActionCommand(" exit ");

		miUndo.setActionCommand(" undo ");
		miCut.setActionCommand(" cut ");
		miCopy.setActionCommand(" copy ");
		miPaste.setActionCommand(" paste ");
		miDelete.setActionCommand(" delete ");
		miFind.setActionCommand(" find ");
		miNext.setActionCommand(" next ");
		miReplace.setActionCommand(" replace ");

		miNewLine.setActionCommand(" newLine ");
		miAbout.setActionCommand(" about ");

		pmUndo.setActionCommand(" undo ");
		pmCut.setActionCommand(" cut ");
		pmCopy.setActionCommand(" copy ");
		pmPaste.setActionCommand(" paste ");
		pmDelete.setActionCommand(" delete ");

		this.setSize(500, 500);
		this.setLocation(300, 150);
		this.setJMenuBar(menubar);

		meFile.setFont(new Font(" 宋体 ", Font.BOLD, 15));
		meEdit.setFont(new Font(" 宋体 ", Font.BOLD, 15));
		meStyle.setFont(new Font(" 宋体 ", Font.BOLD, 15));
		meHelp.setFont(new Font(" 宋体 ", Font.BOLD, 15));

		menubar.add(meFile);
		menubar.add(meEdit);
		menubar.add(meStyle);
		menubar.add(meHelp);

		meFile.add(miCreate);
		meFile.add(miOpen);
		meFile.add(miSave);
		meFile.add(miSaveAs);
		meFile.addSeparator();
		meFile.add(miExit);

		meEdit.add(miUndo);
		meEdit.addSeparator();
		meEdit.add(miCut);
		meEdit.add(miCopy);
		meEdit.add(miPaste);
		meEdit.add(miDelete);
		meEdit.addSeparator();
		meEdit.add(miFind);
		meEdit.add(miNext);
		meEdit.addSeparator();
		meEdit.add(miReplace);

		meStyle.add(miNewLine);
		meStyle.add(smLookFeel);
		smLookFeel.add(metal);
		smLookFeel.add(motif);
		smLookFeel.add(windows);

		meHelp.add(miAbout);
		// 添加到右键弹出菜单
		popupMenu = new JPopupMenu();
		popupMenu.add(pmUndo);
		popupMenu.addSeparator();
		popupMenu.add(pmCut);
		popupMenu.add(pmCopy);
		popupMenu.add(pmPaste);
		popupMenu.add(pmDelete);
		// 添加按钮事件监听
		meHelp.addActionListener(this);
		miCreate.addActionListener(this);
		miOpen.addActionListener(this);
		miSave.addActionListener(this);
		miSaveAs.addActionListener(this);
		miExit.addActionListener(this);

		miUndo.addActionListener(this);
		miCut.addActionListener(this);
		miCopy.addActionListener(this);
		miPaste.addActionListener(this);
		miDelete.addActionListener(this);
		miFind.addActionListener(this);
		miNext.addActionListener(this);
		miReplace.addActionListener(this);

		miNewLine.addItemListener(this);
		miAbout.addActionListener(this);
		metal.addActionListener(this);
		motif.addActionListener(this);
		windows.addActionListener(this);

		// 添加右键按钮事件监听器
		pmUndo.addActionListener(this);
		pmCut.addActionListener(this);
		pmCopy.addActionListener(this);
		pmPaste.addActionListener(this);
		pmDelete.addActionListener(this);

		// 文本区内容没有选中时某些按钮不可用
		miCut.setEnabled(false);
		miCopy.setEnabled(false);
		miDelete.setEnabled(false);

		pmCut.setEnabled(false);
		pmCopy.setEnabled(false);
		pmDelete.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane(text);
		getContentPane().add(scrollPane, "Center");
		text.setFont(new Font(" Fixedsys ", Font.TRUETYPE_FONT, 15));
		setVisible(true);

		// 添加键盘输入监听器
		text.addFocusListener(new MyFocusAdapter());
		// 添加鼠标监听器，用于激活右键弹出菜单
		text.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popupMenu.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});
		// 添加窗口关闭监听器
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { // 询问是否保存时选择撤消
				if (askForSave() == 3) {
					return;
				}
				System.exit(0);
			}
		});


	}

	////////////////////////// Methods //////////////////////////////////// /
	// 打开
	public void open() {
		JFileChooser jc = new JFileChooser();
		jc.showOpenDialog(this);
		File f = jc.getSelectedFile();
		if (f == null) // 没有选择文件则退出
		{
			return;
		}
		file = f; // file 是File类的对象,为本类属性,在保存当前内容时用
		this.setTitle(f.getName() + " --记事本 ");
		FileReader fr = null;
		int len = (int) f.length();
		char[] ch = new char[len];
		int num = 0;
		try {
			fr = new FileReader(f);
			while (fr.ready()) {
				num += fr.read(ch, num, len - num);
			}
			// 保存在属性strtext中，为了便于撤消恢复及监视内容是否改变
			strtext = new String(ch, 0, num);
			haveCreated = false;
			text.setText(strtext);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, " 出错:  " + e.getMessage());
		} finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, " 出错:  " + e.getMessage());
			}
		}
	}

	// 保存
	public void save(File f) {
		String saveStr = text.getText();
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
			fw.write(saveStr);
			fw.flush();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, " 出错:  " + e.getMessage());
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(this, " 出错:  " + e.getMessage());
			}
		}
		haveCreated = false;
		JOptionPane.showMessageDialog(this, " 文件保存成功! ");
	}

	// 另存为
	public void saveAs() {
		JFileChooser fs = new JFileChooser();
		fs.showSaveDialog(this);
		File f = fs.getSelectedFile();
		if (f != null) {
			save(f);
			this.setTitle(f.getName() + " --记事本 ");
			file = f;
		}

	}
	/** */
	/**
	 * 如果显示的文件内容与原来有改变，询问是否保存
	 *
	 * @return int 0: no operation  1:yes  2:no   3:cancel -1:error return
	 */
	public int askForSave() {
		if (haveCreated && "".equals(text.getText())) {
			return 0;
		}

		if (!text.getText().equals(strtext)) {
			String fn;
			if (file != null) {
				fn = "" + file.getName();
			} else {
				fn = " 未命名 ";
			}
			int i = JOptionPane.showConfirmDialog(this, " 文件 " + fn + " 的文字已经改变。 " +
					" /n要保存文件吗？ ", " 记事本 ", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (i == JOptionPane.YES_OPTION) {
				if (file == null) {
					saveAs();
				} else {
					save(file);
				}
				return 1;

			}
			if (i == JOptionPane.NO_OPTION) {
				return 2;
			}
			if (i == JOptionPane.CANCEL_OPTION) {
				return 3;
			}
		}
		return -1;
	}

	// 查找
	public void find(String str, int cur) {
		int i = text.getText().indexOf(str, cur);
		if (i >= 0) {
			this.text.setSelectionStart(i); // 使找到的字符串反白选中
			this.text.setSelectionEnd(i + str.length());
			findIndex = ++i; // 用于查找下一个
		} else {
			JOptionPane.showMessageDialog(this, " 查找完毕！ ", " 记事本 ",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	// 替换全部
	public void replaceAll(String fromStr, String toStr, int cur, int end) {
		if (cur <= end) {
			int i = text.getText().indexOf(fromStr, cur);
			if (i >= 0) {
				text.setSelectionStart(i); // 使找到的字符串反白选中
				text.setSelectionEnd(i + fromStr.length());
				text.replaceSelection(toStr); // 替换
				cur = ++i;
			} else {
				JOptionPane.showMessageDialog(this, " 替换完毕！ ", " 记事本 ",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			replaceAll(fromStr, toStr, cur, end);  // 递归查找与替换
		}
	}

	// 切换观感
	public void changeLookFeel(Component c, String plafName) {
		try {
			UIManager.setLookAndFeel(plafName);
			SwingUtilities.updateComponentTreeUI(c);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, " 观感加载失败！ ", " 记事本 ",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
//////////////////////// 实现监听类的方法(本类实现了相应接口) ////////////////////////////////// /

	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			text.setLineWrap(true);
		} else {
			text.setLineWrap(false);
		}
	}

	public void actionPerformed(ActionEvent e) {
		String com = e.getActionCommand();
		if (com.equals(" create ")) {     // 当前显示的文件内容如有改变，询问是否保存
			// 返回:询问是否保存时选择否
			// 返回3:询问是否保存时选择撤消
			if (askForSave() == 3) {
				return;
			}
			text.setText("");
			file = null;
			this.setTitle(" 未命名--记事本 ");
			strtext = "";
			haveCreated = true;
		}
		if (com.equals(" open ")) { // 询问是否保存时选择撤消
			if (askForSave() == 3) {
				return;
			}
			open();
			strtext = text.getText();
		}
		if (com.equals(" saveAs ")) {
			saveAs();
			strtext = text.getText();
		}
		if (com.equals(" save ")) {
			if (haveCreated || file == null) {
				saveAs();
			} else {
				save(file);
			}
			strtext = text.getText();
		}
		if (com.equals(" undo ")) {
			text.setText(strtext);
		}
		if (com.equals(" cut ")) {
			// 先保存文本区内容,供撤消后恢复使用
			strtext = text.getText();
			text.cut();
		}
		if (com.equals(" copy ")) {
			text.copy();
		}
		if (com.equals(" paste ")) {
			strtext = text.getText();
			text.paste();
		}
		if (com.equals(" delete ")) {
			strtext = text.getText();
			text.replaceSelection("");
		}
		if (com.equals(" find ")) {
			findIndex = 0;
		}
		if (com.equals(" next ")) {
			// 没有选中内容则从头开始查找
			String str = text.getSelectedText();
			if ("".equals(str) || str == null) {
				findIndex = 0;
			}
			if (str != null) {
				find(str, text.getSelectionStart() + 1);
			}
		}
		if (com.equals(" about ")) {
			JOptionPane.showMessageDialog(this, " 我的记事本  V1.0/n作者：ZhiJian Zhang /n2005/5/25 Copyright ",
					" 关于我的记事本 ", JOptionPane.INFORMATION_MESSAGE);
		}
		// 观感选择
		if (e.getActionCommand().equals(" Metal ")) {
			String metal_str = " javax.swing.plaf.metal.MetalLookAndFeel ";
			changeLookFeel(this, metal_str);
		}
		if (e.getActionCommand().equals(" Motif ")) {
			String motif_str = " com.sun.java.swing.plaf.motif.MotifLookAndFeel ";
			changeLookFeel(this, motif_str);
		}
		if (e.getActionCommand().equals(" Windows ")) {
			// String windows_str="com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
			String windows_str = " com.sun.java.swing.plaf.windows.WindowsLookAndFeel ";
			changeLookFeel(this, windows_str);
		}
		if (e.getActionCommand().equals(" exit ")) {
			if (askForSave() == 3) {
				return;
			}
			System.exit(0);
		}
	}

	////////////////////////////// /内部监听类 ////////////////////////////
// 监听文本区变动
	class MyFocusAdapter extends FocusAdapter { // 如果文本区没有选中的内容，则相应菜单项不可用

		public void focusLost(FocusEvent e) {
			String str = text.getSelectedText();
			if (!"".equals(str) && str != null) {
				// 菜单项
				miCut.setEnabled(true);
				miCopy.setEnabled(true);
				miDelete.setEnabled(true);
				// 右键菜单项
				pmCut.setEnabled(true);
				pmCopy.setEnabled(true);
				pmDelete.setEnabled(true);
			} else {
				miCut.setEnabled(false);
				miCopy.setEnabled(false);
				miDelete.setEnabled(false);

				pmCut.setEnabled(false);
				pmCopy.setEnabled(false);
				pmDelete.setEnabled(false);
			}
		}
	}

	public static void main(String[] args) {
		new MyNotepad();

	}

} // End of main class

////////////////////////// 外部类 ////////////////////////////////
/////////////////////查找对对话框类 ////////////////////////////
class FindDialog extends JDialog implements ActionListener {
	// 引入np属性是为了通过构造器的参数传入NotePad的对象,
	// 从而方便访问其内部属性与方法
	MyNotepad np;
	JTextField tex;
	JLabel label;
	JButton find;
	JButton exit;

	public FindDialog(MyNotepad owener) {
		super(owener, " 查找 ", false);
		this.np = owener;
		label = new JLabel("   查找内容 ");
		tex = new JTextField(5);
		find = new JButton(" 查找下一个 ");
		exit = new JButton(" 取消 ");
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(2, 2));
		contentPane.add(label);
		contentPane.add(tex);
		contentPane.add(find);
		contentPane.add(exit);
		this.setSize(210, 80);
		this.setLocation(450, 350);
		this.setResizable(false);

		find.addActionListener(this);
		exit.addActionListener(this);
		tex.addKeyListener(new MyKeyAdapter());
		find.setEnabled(false);
		this.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		String str = np.text.getSelectedText();
		if ("".equals(str) || str == null) {
			np.findIndex = 0;
		}
		if (e.getSource() == find) {
			np.find(tex.getText(), np.findIndex);
		} else if (e.getSource() == exit) {
			this.dispose();
		}
	}

	class MyKeyAdapter extends KeyAdapter {
		public void keyReleased(KeyEvent e) {
			if (np.text.getSelectedText() != "") {
				find.setEnabled(true);
			}
		}
	}
}

////////////////////// 查找替换对话框 //////////////////////// /
class ReplaceDialog extends JDialog implements ActionListener {
	MyNotepad np;
	JTextField ft;
	JTextField rt;
	JLabel b1;
	JLabel b2;
	JButton find;
	JButton rp;
	JButton rpa;
	JButton exit;

	public ReplaceDialog(MyNotepad owener) {
		super(owener, " 替换 ", false);
		this.np = owener;
		b1 = new JLabel(" 查找内容 ");
		b2 = new JLabel(" 替  换  为　 ");
		ft = new JTextField(8);
		// 初始状态将已查找到的字符串放在此文本框中
		ft.setText(owener.text.getSelectedText());
		rt = new JTextField(8);

		find = new JButton(" 查找下一个 ");
		rp = new JButton(" 替     换 ");
		rpa = new JButton(" 全部替换 ");
		exit = new JButton(" 取       消 ");
		Container cp = getContentPane();
		JPanel p1 = new JPanel();
		JPanel p2 = new JPanel();

		p1.setLayout(new GridLayout(2, 3, 5, 2));
		p1.add(b1);
		p1.add(ft);
		p1.add(find);
		p1.add(b2);
		p1.add(rt);
		p1.add(rp);
		cp.add(p1, BorderLayout.NORTH);

		p2.setLayout(new FlowLayout(FlowLayout.RIGHT));
		p2.add(rpa);
		p2.add(exit);
		cp.add(p2, BorderLayout.SOUTH);

		this.setSize(350, 120);
		this.setLocation(400, 350);
		this.setResizable(false);

		find.addActionListener(this);
		rp.addActionListener(this);
		rpa.addActionListener(this);
		exit.addActionListener(this);

		ft.addKeyListener(new MyKeyAdapter());
		rt.addKeyListener(new MyKeyAdapter());
		find.setEnabled(false);
		rp.setEnabled(false);
		rpa.setEnabled(false);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == find) {
			/**/ /* String str=np.text.getSelectedText();
		   if(str==""||str==null)
           {
               np.findIndex=0;
           } */
			if (!ft.getText().equals(np.findStr)) {
				np.findIndex = 0;
			}
			np.findStr = ft.getText();
			np.find(np.findStr, np.findIndex);
		}
		if (e.getSource() == rp) {
			String str = np.text.getSelectedText();
			if (!"".equals(str) && str != null) {
				np.text.replaceSelection(rt.getText());
			}
		}
		if (e.getSource() == rpa) {
			int n = np.text.getText().length();
			np.replaceAll(ft.getText(), rt.getText(), 0, n);
		} else if (e.getSource() == exit) {
			this.dispose();
		}
	}

	class MyKeyAdapter extends KeyAdapter { // 如果查找或替换框内没有内容则相应按钮不可用

		public void keyReleased(KeyEvent e) {
			if (!"".equals(ft.getText())) {
				find.setEnabled(true);
			}
			if (!"".equals(rt.getText())) {
				rp.setEnabled(true);
				rpa.setEnabled(true);
			}
		}
	}
}