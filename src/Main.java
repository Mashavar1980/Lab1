import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {    //точка входа в джава-прогу
        System.out.println("Hello world!");

        int[] koords = new int[5]; //создаёт массив для хранение целых чисел
        JTextField smallField = new JTextField("300, 250, 25, 45, 20"); //создаёт текстовое поле
        JTextField numbers = new JTextField("0,1,2,3,4,5,6,7,8,9,М,А,Р,И,Я"); //создаёт текстовое поле
        JButton button = new JButton("Ввод");  //создаёт кнопку с надписью Ввод
        JButton clearButton = new JButton("Очистка"); //создаёт кнопку с надписью Очистка
        JFrame f = new JFrame("Swing Paint Demo");  //создаёт главное окно приложения с заголовком
        MyPanel myPanel = new MyPanel();
        ActionListener PrintLine = new ActionListener() {   //создаёт реализацию интерфейса для обработки нажатия кнопки
            public void actionPerformed(ActionEvent e) {   //внутри этого метода описано,что будет при нажатии на кнопку

                String[] parm = smallField.getText().replaceAll(" ", "").split(",");  //получает текст из smallField ,удаляет все пробелы и разбивает строку на эл-ты массива parm через запятую
                if (parm.length != parm.length){
                    JOptionPane.showMessageDialog(null, "Ошибка в количестве параметров");
                }
                for (int i = 0; i < parm.length; i++){    //проходит по всем эл-там массива arm  преобразует их в целые числа,сохраняя результат в массиве koords
                    koords[i] = getInteger(parm[i]);
                }
                myPanel.setX(koords[0]);               //
                myPanel.setY(koords[1]);               //
                myPanel.setWidth(koords[2]);           //устанавливает значения координат,ширины,высоты и шага для объекта myPanel
                myPanel.setHeight(koords[3]);          //
                myPanel.setStep(koords[4]);            //
                myPanel.setSymbols(numbers.getText()); //устанавливает строку символов для объекта myPanel
                myPanel.repaint();                     //
                f.add(myPanel);                        //обновляет панель myPanel,добавляет её в фрейм f,автоматически
                f.pack();                              //подгоняет размер фрейма под содержимое и делает фрейм видимым
                f.setVisible(true);                    //
            }
        };

        clearButton.addActionListener(new ActionListener() {  //
            public void actionPerformed(ActionEvent e) {      //
                smallField.setText("");                       //добавляет слушатель событий на кнопку.При нажатии кнопки очищает
                numbers.setText("");                          //текстовые поля smallFrield и numbers,очищает панель myPanel и обновляет её
                myPanel.clear();                              //
                myPanel.repaint();                            //
            }
        });

        numbers.addActionListener(PrintLine);        //добавляет общие слушатели событий для текстового поля numbers и кнопки
        button.addActionListener(PrintLine);         //button,преиспользуя вышеописанный объект Print_Line

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);              //устанавливает действие по умолч. при закрытии окна - завершение программы
        JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEFT)); //создаёт панель contents  менеджером компоновки FlowLayout,выравненным по левому краю

        contents.add(numbers);      //
        contents.add(smallField);   //добавляет текстовые поля и
        contents.add(button);       //кнопки на панель сontents
        contents.add(clearButton);  //

        f.setContentPane(contents); //устанавливает содержимое фрейма f на панель contents

        myPanel.repaint();   //
        f.add(myPanel);      //обновляет панель myPanel ,добавляет её во
        f.pack();            //фрейм и делает его видимым
        f.setVisible(true);  //
    }

    private static int getInteger(String parm){   //пытается преобразовать в целое число,если не получается возвращает 0
        try {
            return Integer.parseInt(parm);
        }
        catch (Exception e){
            new Exception("Это не цифра");
        }
        return 0;
    }
}