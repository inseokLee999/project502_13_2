package org.choongang.students.controllers;

import org.choongang.AfterLoginMenu.constants.SubMenu;
import org.choongang.global.AbstractController;
import org.choongang.global.Router;
import org.choongang.global.Service;
import org.choongang.main.MainRouter;
import org.choongang.students.constants.StuInfoMenu;
import org.choongang.students.entities.StuInfo;
import org.choongang.students.services.StuinfoServiceLocator;

import org.choongang.template.Templates;

import java.util.List;
import java.util.stream.Collectors;

public class ReadStuinfoController extends AbstractController {

    @Override
    public void show() {
        Router router = MainRouter.getInstance();
        while(true) {
            String str = promptWithValidation("조회할 학생 번호(종료 입력시 종료): ", s -> {
                if(s.equals("종료")){
                    router.change(SubMenu.PRIVACY);
                    return false;
                }
                return !s.isBlank();
            });
            int userNo = Integer.parseInt(str);

            try {
                Service service = StuinfoServiceLocator.getInstance().find(StuInfoMenu.READ);
                List<StuInfo> stuInfos = service.process3(userNo);
                String stu = stuInfos.stream()
                        .map(r->String.format("학년 : %s, 성별 : %s, 학기 : %s, 학과 :%s, 학점 : %d, 입학 년도 : %s, 이메일 : %s, 전화번호 : %d ",r.getGrade(),r.getGender(),r.getSemester(),r.getDept(),r.getStuCredit(),r.getAdYr(),r.getEMail(),r.getTel(),r.getUserNo())).collect(Collectors.joining("\n"));
                //학생 정보 조회 성공시
                Templates.getInstance().render(StuInfoMenu.READ,()->stu);
                System.out.println("학생 정보 조회 성공");
                router.change(StuInfoMenu.READ);
                break;
            }catch (RuntimeException e){
                System.err.println("학생 정보 조회 실패");
                System.out.println();
                router.change(StuInfoMenu.READ);
            }
        }
    }
}