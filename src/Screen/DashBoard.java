package Screen;
import java.util.Scanner;
public class DashBoard {
    public static boolean validate(String n){
        if(n == null){
            return false;
        }
        try{
            int i = Integer.parseInt(String.valueOf(n));
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
        System.out.print("==========================================");
        System.out.print("============= School Manage ==============");
        System.out.print("==========================================");
        System.out.print("[Notice] Nhập 1 trong các số 1-7 để chọn!");
        System.out.print("==========================================");
        System.out.print("[1] >> Quản lý Lớp học.");
        System.out.print("[2] >> Quản lý môn học.");
        System.out.print("[3] >> Quản lý điểm.");
        System.out.print("[4] >> Quản lý học sinh.");
        System.out.print("[5] >> Quản lý giáo viên.");
        System.out.print("[6] >> Quản lý học phí.");
        String option = input.nextLine();
        while(!validate(option)){
            System.out.print("Lựa chọn không hợp lệ, vui lòng nhập 1 trong các số 1-7:");
            option = input.nextLine();
        }
    }
}
