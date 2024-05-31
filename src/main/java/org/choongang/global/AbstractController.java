package org.choongang.global;

import org.choongang.global.constants.MainMenu;
import org.choongang.main.MainRouter;
import org.choongang.template.Templates;

import java.util.Scanner;
import java.util.function.Predicate;

public abstract class AbstractController implements Controller {

    protected Scanner sc;
    public AbstractController(){
        sc = new Scanner(System.in);
    }
    /**
     * 상단 공통 출력 부분
     */
    public void common(){
        System.out.println("학생관리 프로그램 Ver1.0");
        System.out.println(Templates.getInstance().doubleLine());

    }

    /**
     * 입력 항목
     * - 문자 : q, exit, quit - 종료
     * - 숫자 : 메뉴 항목
     *
     */
    public void prompt(){
        System.out.println(Templates.getInstance().doubleLine());
        System.out.println("메뉴 선택 : ");
        String menu = sc.nextLine();
        if(menu.equals("q") || menu.equals("quit") || menu.equals("exit")){
            System.exit(0);//0- 정상종료, 1- 비정상 종료
        }

        try{
            int m = Integer.parseInt(menu);
            change(m);//메뉴변경
        }catch(Exception e){
            System.out.println("메뉴를 숫자로 입력하세요.");
        }
    }
    /**
     *
     * @param message : 항목메세지
     * @param predicate : 판별식
     * @return
     */
    protected String promptWithValidation(String message, Predicate<String> predicate){
        String str = null;
        do{
            System.out.println(message);
            str = sc.nextLine();
        }while (!predicate.test(str));
        return str;
    }
    /**
     * 템플릿 메서드 패턴 : 특정 절차가 고정 되어 있는 경우
     *
     */
    @Override
    public final void run() {
        common();
        show();
        prompt();
    }
    private void change(int menuNo){
        MainMenu menu = null;
        switch(menuNo){
            case 1: menu = MainMenu.JOIN; break; // 회원가입

            case 2: menu = MainMenu.LOGIN; break; // 로그인

            default: menu = MainMenu.MAIN; // 메인메뉴

        }
        // 메뉴 컨트롤러 변경 처리 - Router
        MainRouter.getInstance().change(menu);
    }
}
