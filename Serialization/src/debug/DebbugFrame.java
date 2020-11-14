package debug;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;

import components.Arrays.DSArray;
import components.Fields.DSField;
import components.Fields.FieldType;
import components.Objects.DSObject;
import components.dataBase.DSDataBase;
import components.string.DSString;

public class DebbugFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public DebbugFrame(DSDataBase DB) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setTitle("debbug tree");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		getContentPane().setBackground(Color.darkGray);
		setSize(700, 500);

		var tree = new JTree(buildTree(DB));
		add(new JScrollPane(tree));

	}

	public DefaultMutableTreeNode buildTree(DSDataBase DB) {
		var root = new DefaultMutableTreeNode(DB.getName());
		var objectsNode = new DefaultMutableTreeNode("DSObjects");
		root.add(objectsNode);
		
		for (var object : DB.objects) {
			objectsNode.add(getObjectNode(object));
		}
		
		return root;
	}

	public DefaultMutableTreeNode getObjectNode(DSObject object) {
		var objectnode = new DefaultMutableTreeNode(object.getName());
		var fieldsNode = new DefaultMutableTreeNode("DSFields");
		objectnode.add(fieldsNode);

		for (var field : object.fields) {
			fieldsNode.add(getFieldNode(field));
		}

		var arrayNode = new DefaultMutableTreeNode("DSArrays");
		objectnode.add(arrayNode);
		for (var array : object.arrays) {
			arrayNode.add(getdArrayNode(array));
		}

		var stringNode = new DefaultMutableTreeNode("DSStrings");
		objectnode.add(stringNode);
		for (var string : object.strings) {
			stringNode.add(getdStringNode(string));
		}

		return objectnode;
	}

	public DefaultMutableTreeNode getFieldNode(DSField f) {
		String nodeText = "";
		var dataType = f.dataType;
		switch (dataType) {
		case FieldType.BYTE:
			nodeText = f.getName() + " type: byte= " + f.getByte();
			break;
		case FieldType.CHAR:
			nodeText = f.getName() + " type: char= " + f.getChar();
			break;
		case FieldType.SHORT:
			nodeText = f.getName() + " type: short= " + f.getShort();
			break;
		case FieldType.INT:
			nodeText = f.getName() + " type: int= " + f.getInt();
			break;
		case FieldType.LONG:
			nodeText = f.getName() + " type: long= " + f.getLong();
			break;
		case FieldType.FLOAT:
			nodeText = f.getName() + " type: float= " + f.getFloat();
			break;
		case FieldType.DOUBLE:
			nodeText = f.getName() + " type: double= " + f.getDouble();
			break;
		case FieldType.BOOLEAN:
			nodeText = f.getName() + " type: boolean= " + f.getBoolean();
			break;
		}

		return new DefaultMutableTreeNode(nodeText);
	}

	public DefaultMutableTreeNode getdArrayNode(DSArray a) {
		String nodeText = "";
		var dataType = a.dataType;
		DefaultMutableTreeNode arrayNode = null;

		switch (dataType) {
		case FieldType.BYTE:
			nodeText = a.getName() + " type: byte,  length = " + a.Nelements;
			arrayNode = new DefaultMutableTreeNode(nodeText);
			for (int i = 0; i < a.Nelements; i++)
				arrayNode.add(new DefaultMutableTreeNode(i + "= [" + a.dataByte[i] + "]"));
			return arrayNode;
		case FieldType.CHAR:
			nodeText = a.getName() + " type: char,  length = " + a.Nelements;
			arrayNode = new DefaultMutableTreeNode(nodeText);
			for (int i = 0; i < a.Nelements; i++)
				arrayNode.add(new DefaultMutableTreeNode(i + "= [" + a.dataChar[i] + "]"));
			return arrayNode;
		case FieldType.SHORT:
			nodeText = a.getName() + " type: short,  length = " + a.Nelements;
			arrayNode = new DefaultMutableTreeNode(nodeText);
			for (int i = 0; i < a.Nelements; i++)
				arrayNode.add(new DefaultMutableTreeNode(i + "= [" + a.dataShort[i] + "]"));
			return arrayNode;
		case FieldType.INT:
			nodeText = a.getName() + " type: int,  length = " + a.Nelements;
			arrayNode = new DefaultMutableTreeNode(nodeText);
			for (int i = 0; i < a.Nelements; i++)
				arrayNode.add(new DefaultMutableTreeNode(i + "= [" + a.dataInt[i] + "]"));
			return arrayNode;
		case FieldType.LONG:
			nodeText = a.getName() + " type: long,  length = " + a.Nelements;
			arrayNode = new DefaultMutableTreeNode(nodeText);
			for (int i = 0; i < a.Nelements; i++)
				arrayNode.add(new DefaultMutableTreeNode(i + "= [" + a.dataLong[i] + "]"));
			return arrayNode;
		case FieldType.FLOAT:
			nodeText = a.getName() + " type: float,  length = " + a.Nelements;
			arrayNode = new DefaultMutableTreeNode(nodeText);
			for (int i = 0; i < a.Nelements; i++)
				arrayNode.add(new DefaultMutableTreeNode(i + "= [" + a.dataFloat[i] + "]"));
			return arrayNode;
		case FieldType.DOUBLE:
			nodeText = a.getName() + " type: double   length = " + a.Nelements;
			arrayNode = new DefaultMutableTreeNode(nodeText);
			for (int i = 0; i < a.Nelements; i++)
				arrayNode.add(new DefaultMutableTreeNode(i + "= [" + a.dataDouble[i] + "]"));
			return arrayNode;
		case FieldType.BOOLEAN:
			nodeText = a.getName() + " type: boolean   length = " + a.Nelements;
			arrayNode = new DefaultMutableTreeNode(nodeText);
			for (int i = 0; i < a.Nelements; i++)
				arrayNode.add(new DefaultMutableTreeNode(i + "= [" + a.dataBoolean[i] + "]"));
			return arrayNode;
		}
		return null;
	}

	public DefaultMutableTreeNode getdStringNode(DSString s) {
		String nodeText = s.getName() + " =  " + s.getString();
		return new DefaultMutableTreeNode(nodeText);
	}

}
