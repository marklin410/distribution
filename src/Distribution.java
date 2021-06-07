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
    public static final double g = 2.71; // �������� �����-������� ��� ���� = 0.33

    public static void main(String[] args) {
        double x = 0; //��������� ��������
        System.out.println(m);
       /* try(FileWriter writer = new FileWriter("D:\\javaprojects\\results.txt",false)) {//��������/�������� ����� ��� ������
            for (int i = 1; i <= 100; i++) {//���� �� ��������� 1000 ��������
                x = (((a*x+ c)%m)*10000.0)/m; // ������� ��������� ������������� ������, � ���������� ����� �� 10000 ��� ����������� ������������ ����������
                double gam = gamma(x/10000.0);//����� ������� ������� ��������� �����-������������� ��� ����� �� 0 �� 1
                writer.write(String.format("%.12f",gam)+" "+String.format("%.12f",x/10000.0)+"\n");//������ � ����
            }
        } catch (IOException ex){
            Logger log =  Logger.getLogger(Distribution.class.getName());
            log.log(Level.INFO, ex.getMessage());
        }*/
        //int p = period(x);
    }

    public static double gamma(double x){
        //������ ���������
        double a1=  Math.pow(beta,alpha);
        double a2= Math.pow(x, beta-1);
        double b1=  Math.pow(Math.E,(-x*alpha));
        //������ ����������
        double res = (a1*a2*b1/g);
        return res;
    }

    public static int period(double x){
        int p = 2; //����������� ������
        double xn = (((a*x+ c)%m)); //������ ������� �������� x1
        double x1 = xn;
        xn = (((a*xn+ c)%m));//������ ������� �������� x2 ��� ��������
        double x2 = xn;
        while(xn!=x1){//����� ������� ��������� x1
            p++;
            xn = (((a*xn+ c)%m));
        }
        if((((a*xn+ c)%m))==x2){//�������� ��� ��������� ����� ����� ������� ���������� x1 ��������� � x2
            return p;
        } else{
            return 0;
        }
    }
}
