
 /**
 * InfectStatistic
 * @author HHQ
 * @version 1.0
 */
class InfectStatistic {

    /**
     * Province��
     * @author HHQ
     */
    public class Province {

        /** ʡ������ */
        String provinceName; 
        /** ��Ⱦ���� */
        int ip; 
        /** ���ƻ��� */
        int sp;
        /** ���� */
        int cure;
        /** ���� */
        int dead;

        Province(String provinceName, int ip, int sp, int cure, int dead) {
            this.provinceName = provinceName;
            this.ip = ip;
            this.sp = sp;
            this.cure = cure;
            this.dead = dead;
        }

    }

    /**
     * description����̬��һ�������࣬һЩ��̬�ķ���
     * @author HHQ
     */
    static class ToolMethods {
    
    }

    public static void main(String[] args) {
    }
}
