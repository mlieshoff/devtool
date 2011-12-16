package devtool;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import java.util.List;
import java.util.zip.*;

import javax.swing.*;
import javax.swing.event.*;

public class Main {

    private JFrame frame = new JFrame("DevTool V0.1");
    private JTextArea content = new JTextArea();
    private JTextField searchField = new JTextField();
    private Connector connector;
    private ResultTableModel resultTableModel; 
    private Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    
    private Main() throws Exception {
        connector = new DefaultConnector(new DefaultAdapter(new ZipFile(
                "target/devtool-res-en.zip")));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(640, 480);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2, 5, 5));
        
        panel.add(createSearchPanel());
        panel.add(createContentPanel());
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private Component createSearchPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));

        panel.add("North", createSearchControlPanel());
        panel.add("Center", createResultPanel());

        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        return panel;
    }

    private Component createSearchControlPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(new BorderLayout(5, 5));
        panel.add("Center", searchField);
        panel.add("East", createSearchButton());
        return panel;
    }

    private Component createSearchButton() {
        JButton button = new JButton("Search");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultTableModel.refresh(doSearch(searchField.getText()));
                frame.repaint();
            }
        });
        return button;
    }

    private Component createResultPanel() {
        resultTableModel = new ResultTableModel(doSearch(null));
        final JTable table = new JTable(resultTableModel);
        table.setShowGrid(true);
        
        ListSelectionModel lsm = table.getSelectionModel();
        lsm.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    int index = table.getSelectedRow();
                    Key key = resultTableModel.getKey(index);
                    List<Content> list = connector.get(key);
                    StringBuilder s = new StringBuilder();
                    for(Content content : list) {
                        s.append("*** ");
                        s.append(content.getName());
                        s.append(" ***\n\n");
                        s.append(content.getText());
                        s.append("\n\n");
                    }
                    content.setText(s.toString());
                }
            }
        });
        table.setSelectionModel(lsm);
        lsm.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);         
        
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    private Component createContentPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));

        panel.add("North", createContentControlPanel());
        panel.add("Center", content);

        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        return panel;
    }

    private Component createContentControlPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(new BorderLayout(5, 5));
        panel.add("East", createCopyButton());
        return panel;
    }

    private Component createCopyButton() {
        JButton button = new JButton("Copy to clipboard");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clipboard.setContents(new StringSelection(content.getText()), null);
            }
        });
        return button;
    }

    private List<Key> doSearch(String query) {
        return connector.search(query);
    }

    public static void main(String[] args) {
        try {
            new Main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
