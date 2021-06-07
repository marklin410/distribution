import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Distribution {
    public static final long  a = 1664525;
    public static final long m =  (int)Math.pow(2, 32);
    public static final long c=1013904223;
    public static final double alpha = 3.5;
    public static final double beta = 1.0/3;
    public static final double g = 2.71; // значение гамма-функции для бета = 0.33

    public static void main(String[] args) {
        double x = 0; //начальное значение
        System.out.println(m);
       /* try(FileWriter writer = new FileWriter("D:\\javaprojects\\results.txt",false)) {//создание/открытие файла для записи
            for (int i = 1; i <= 100; i++) {//цикл на генерацию 1000 значений
                x = (((a*x+ c)%m)*10000.0)/m; // формула линейного конгруэнтного метода, с умножением числа на 10000 для корректного последующего округления
                double gam = gamma(x/10000.0);//вызов расчета функции плотности гамма-распределения для чисел от 0 до 1
                writer.write(String.format("%.12f",gam)+" "+String.format("%.12f",x/10000.0)+"\n");//запись в файл
            }
        } catch (IOException ex){
            Logger log =  Logger.getLogger(Distribution.class.getName());
            log.log(Level.INFO, ex.getMessage());
        }*/
        //int p = period(x);
    }

    public static double gamma(double x){
        //расчет слагаемых
        double a1=  Math.pow(beta,alpha);
        double a2= Math.pow(x, beta-1);
        double b1=  Math.pow(Math.E,(-x*alpha));
        //расчет результата
        double res = (a1*a2*b1/g);
        return res;
    }

    public static int period(double x){
        int p = 2; //минимальный период
        double xn = (((a*x+ c)%m)); //расчет первого значения x1
        double x1 = xn;
        xn = (((a*xn+ c)%m));//расчет второго значения x2 для проверки
        double x2 = xn;
        while(xn!=x1){//поиск первого вхождения x1
            p++;
            xn = (((a*xn+ c)%m));
        }
        if((((a*xn+ c)%m))==x2){//проверка что следующее число после первого совпадения x1 совпадает с x2
            return p;
        } else{
            return 0;
        }
    }
}
