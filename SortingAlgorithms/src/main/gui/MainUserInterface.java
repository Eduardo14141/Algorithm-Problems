package main.gui;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class MainUserInterface extends JFrame{
    
    private JButton selection_sort_button;
    private JButton bubble_sort_button;
    private JButton Shell_sort_button;
    private JButton quick_sort_button;
    private JPanel main_panel;
    private JPanel array_panel;
    private JPanel buttons_panel;
    private JPanel answer_panel;
    private JTextField array_text_field;
    private JLabel response;
    private JLabel algorithm_name;
    private JLabel response_time;
    private final SortAlgorithms sort;
    private final Pattern pattern = Pattern.compile("^([-]?[0-9]+([.][0-9]+)?([,][-]?[0-9]+([.][0-9]+)?)*)$");
    
    public MainUserInterface(){
        this.sort = new SortAlgorithms();
        initComponents();
    }
    private void initComponents(){
        Container container = this.getContentPane();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(900, 700));
        this.setMinimumSize(new Dimension(600, 150));
        this.setMaximumSize(new Dimension(1500, 1200));
        this.setTitle("Métodos de ordenamiento");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        int width = container.getWidth();
        int height = container.getHeight();
        final Font font = new Font("Serif", Font.PLAIN, 16);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(15, 15, 15, 15);
        
        final EmptyBorder margin = new EmptyBorder(height/16, width/16, height/16, width/16);
        
        this.main_panel = new JPanel(new BorderLayout());
        this.array_panel = initArrayPanel(constraints, margin, font);
        this.buttons_panel = initButtonsPanel(constraints, margin, font);
        this.answer_panel = initAnswerPanel(constraints, margin, font);
        
        this.main_panel.add(this.array_panel, BorderLayout.NORTH);
        this.main_panel.add(this.buttons_panel, BorderLayout.CENTER);
        this.main_panel.add(this.answer_panel, BorderLayout.SOUTH);
        
        this.add(this.main_panel);
    }
    private JPanel initArrayPanel(GridBagConstraints constraints, EmptyBorder margin, Font font){
        array_panel = new JPanel(new GridBagLayout());
        array_panel.setBorder(margin);
        
        //Título del panel
        JLabel title = new JLabel("Arreglo a ordenar");
        title.setFont(new Font("Serif", Font.BOLD, 18));
        constraints.gridy = 0;
        constraints.gridx = 0;
        array_panel.add(title, constraints);
        
        //Label para el TextField
        JLabel instructions = new JLabel("Ingresa tu arreglo de la siguiente manera: [3, 7, -4, 0.8, 2, ..., 9]");
        instructions.setFont(font);
        constraints.gridy = 1;
        constraints.gridx = 0;
        array_panel.add(instructions, constraints);
        
        //Declarando el TextField
        array_text_field = new JTextField();
        array_text_field.setFont(font);
        array_text_field.setToolTipText("Por favor, ingresa los decimales menores a uno de la siguiente manera: 0.1");
        //Valida los elementos del array, que sean números reales separados por coma
        //^([-]?[0-9]+([.][0-9]+)?([,][-]?[0-9]+([.][0-9]+)?)*)$
        constraints.gridy = 1;
        constraints.gridx = 1;
        constraints.weightx = 2;
        array_text_field.setSize(new Dimension(100, 30));
        array_panel.add(array_text_field, constraints);
        
        return array_panel;
    }
    private JPanel initButtonsPanel(GridBagConstraints constraints, EmptyBorder margin, Font font){
        this.buttons_panel = new JPanel(new GridBagLayout());
        this.buttons_panel.setBorder(margin);
        constraints.weightx = 1;
        //Título del panel:
        JLabel title = new JLabel("Escoge el método de ordenamiento");
        title.setFont(new Font("Serif", Font.BOLD, 18));
        constraints.gridy = 0;
        constraints.gridx = 0;
        this.buttons_panel.add(title, constraints);
        
        //Ordenamiento por seleción
        //Declarando botón
        this.selection_sort_button = new JButton("Ordenamiento por selección");
        this.selection_sort_button.setMargin(new Insets(10, 15, 10, 15));
        this.selection_sort_button.setFont(font);
        this.selection_sort_button.addActionListener(this::selectionSortEventListener);
        constraints.gridy = 1;
        constraints.gridx = 0;
        this.buttons_panel.add(this.selection_sort_button, constraints);
        
        //Ordenamiento por el método burbuja
        //Declarando botón
        this.bubble_sort_button = new JButton("Método burbuja");
        this.bubble_sort_button.setMargin(new Insets(10, 15, 10, 15));
        this.bubble_sort_button.setFont(font);
        this.bubble_sort_button.addActionListener(this::bubbleSortEventListener);
        constraints.gridy = 1;
        constraints.gridx = 2;
        this.buttons_panel.add(this.bubble_sort_button, constraints);
        this.add(this.buttons_panel);
        
        //Ordenamiento por el método burbuja recursivo
        //Declarando botón
        this.Shell_sort_button = new JButton("Ordenamiento Shell");
        this.Shell_sort_button.setMargin(new Insets(10, 15, 10, 15));
        this.Shell_sort_button.setFont(font);
        this.Shell_sort_button.addActionListener(this::ShellSortEventListener);
        constraints.gridy = 2;
        constraints.gridx = 0;
        this.buttons_panel.add(this.Shell_sort_button, constraints);
        
        //Ordenamiento por el método burbuja recursivo
        //Declarando botón
        this.quick_sort_button = new JButton("Ordenamiento rápido");
        this.quick_sort_button.setMargin(new Insets(10, 15, 10, 15));
        this.quick_sort_button.setFont(font);
        constraints.gridy = 2;
        constraints.gridx = 2;
        this.buttons_panel.add(this.quick_sort_button, constraints);
        return this.buttons_panel;
    }
    private JPanel initAnswerPanel(GridBagConstraints constraints, EmptyBorder margin, Font font){
        this.answer_panel = new JPanel(new GridBagLayout());
        this.answer_panel.setBorder(margin);
        constraints.weightx = 1;
        //Título del panel
        JLabel title = new JLabel("Respuesta");
        title.setFont(new Font("Serif", Font.BOLD, 18));
        constraints.gridy = 0;
        constraints.gridx = 0;
        this.answer_panel.add(title, constraints);
        
        //Label tiempo de respuesta
        JLabel response_time_label = new JLabel("Tiempo de respuesta: ");
        response_time_label.setFont(font);
        constraints.gridy = 1;
        constraints.gridx = 0;
        this.answer_panel.add(response_time_label, constraints);
        
        //Tiempo de respuesta
        this.response_time = new JLabel();
        this.response_time.setFont(font);
        constraints.gridy = 1;
        constraints.gridx = 1;
        this.answer_panel.add(this.response_time, constraints);
        
        //Arreglo ordenado
        this.algorithm_name = new JLabel("Arreglo ordenado: ");
        this.algorithm_name.setFont(font);
        constraints.gridy = 2;
        constraints.gridx = 0;
        this.answer_panel.add(this.algorithm_name, constraints);
        
        //Arreglo ordenado
        this.response = new JLabel();
        this.response.setFont(font);
        constraints.gridy = 2;
        constraints.gridx = 1;
        constraints.weightx = 2;
        this.answer_panel.add(this.response, constraints);
        return this.answer_panel;
    }
    
    private void selectionSortEventListener(ActionEvent evt){
        ArrayList<Double> array = getArray();
        if(array != null)
            setResponse(sort.selectionSort(array));
    }
    private void bubbleSortEventListener(ActionEvent evt){
        ArrayList<Double> array = getArray();
        if(array != null)
            setResponse(sort.bubbleSort(array));
    }
    private void ShellSortEventListener(ActionEvent evt){
        ArrayList<Double> array = getArray();
        if(array != null)
            setResponse(sort.ShellSort(array));
    }
    //Obtiene el array del JTextField
    //Lo valida y lo convierte en un ArrayList<Double>
    private ArrayList<Double> getArray(){
        String text = this.array_text_field.getText().replaceAll("\\s","")
                .replace("[", "").replace("]", "");
        Matcher match = this.pattern.matcher(text);
        if(match.matches()){
            String[] string_array = text.split(",");
            ArrayList<Double> array = new ArrayList<>();
            for(String str: string_array)
                array.add(Double.parseDouble(str));
            return array;
        }else{
            String message = "El arreglo no tiene un formato válido, "
                    + "recuerda poner los decimales menores a 1 iniciando con 0";
            String title = "Formato no válido";
            JOptionPane.showMessageDialog(this.getContentPane(), message, 
                    title, JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
    //Método general de los botones para mostrar los resultados
    private void setResponse(Response response){
        String array = response.getArray().toString().replaceAll("\\,", ", ");
        this.response.setText("<html>".concat(array).concat("</html>"));
        this.algorithm_name.setText(response.getAlgorithm_name());
        this.response_time.setText(String.valueOf(response.getTime()).concat(" nano segundos"));
    }
}