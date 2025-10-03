package Screen;
import java.util.Scanner;
public class DashBoard {
    public static boolean validate(String n){
        if(n == null){
            return false;
        }
        try{
            int i = Integer.parseInt(n);
            if(i<=0 || i>=8){
                return false;
            }
        } catch (Exception e){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("==========================================");
        System.out.println("============= School Manage ==============");
        System.out.println("==========================================");
        System.out.println("[Notice] Nhập 1 trong các số 1-7 để chọn!");
        System.out.println("==========================================");
        System.out.println("[1] >> Quản lý Lớp học.");
        System.out.println("[2] >> Quản lý môn học.");
        System.out.println("[3] >> Quản lý điểm.");
        System.out.println("[4] >> Quản lý học sinh.");
        System.out.println("[5] >> Quản lý giáo viên.");
        System.out.println("[6] >> Quản lý học phí.");
        System.out.println("[7] >> Thoát!");
        System.out.print("Bạn chọn:");
        String option = input.nextLine();
        while(!validate(option)){
            System.out.print("Lựa chọn không hợp lệ, vui lòng chọn một trong các số 1-7:");
            option = input.nextLine();
        }
    }
}
