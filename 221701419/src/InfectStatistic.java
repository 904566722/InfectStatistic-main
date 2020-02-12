import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

/**
 * InfectStatistic
 * @author HHQ
 * @version 1.4
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
        
        /** ��Ⱦ�������� */
        public void increaseIp(int newIpNum) {
            ip += newIpNum;
        }

        /** ��Ⱦ�������� */
        public void decreaseIp(int ipNum) {
            ip -= ipNum;
        }

        /** ���ƻ������� */
        public void increaseSp(int newSpNum) {
            sp += newSpNum;
        }
        
        /** ���ƻ��߼��� */
        public void decreaseSp(int spNum) {
            sp -= spNum;
        }

        /** �������� */
        public void increaseCure(int newCureNum) {
            cure += newCureNum;
        }

        /** �������� */
        public void increaseDead(int newDeadNum) {
            dead += newDeadNum;
        }
        
        public String getProvinceName() {
            return provinceName;
        }

        public int getIp() {
            return ip;
        }

        public int getSp() {
            return sp;
        }

        public int getCure() {
            return cure;
        }

        public int getDead() {
            return dead;
        }

    }

    /**
     * description����̬��һ�������࣬һЩ��̬�ķ���
     * @author HHQ
     */
    static class ToolMethods {
        
        /**
         * description����һ���ַ����Կո�" "�ָ�
         * @param string ������ַ���
         * @return ����ֵΪ�ָ�����������
         */
        public static int numAfterSplit(String string) {
            String[] afterSplitStrings = string.split(" ");
            return afterSplitStrings.length;
        }
        
        /**
         * description����ȡһ���ַ���ǰ������
         * @param string ������ַ���
         * @return ����ֵΪȡ�õ���ֵint
         */
        public static int getNumber(String string) {
            for (int i = 0; i < string.length(); i++) {
                if (Character.isDigit(string.charAt(i))) {
                    ;
                } else {
                    string = string.substring(0, i);
                    break;
                }
            }

            return Integer.parseInt(string);
        }
        
        /**
         * description���õ�Ҫ�޸����ݵ�ʡ������modifyProvinceName
         * @param strings �ָ����ַ�������
         * @return ����ֵΪʡ���������飬ֻ��һ��ʡ��ʱ�ڶ���Ϊ��
         */
        public static String[] getNeedModifyProvinceNames(String[] strings) {
            int len = strings.length;
            String[] resStrings = new String[2];
            if (len == 3 || len == 4) {
                resStrings[0] = strings[0];
                resStrings[1] = "";
            } else if (len == 5) {
                resStrings[0] = strings[0];
                resStrings[1] = strings[3];
            }
            return resStrings;
        }
        
        /**
         * description���жϲ�������
         * @param strings �ָ����ַ�������
         * @return ����ֵ��������ID��1~8��
         */
        public static int getOperateType(String[] strings) {
            int len = strings.length;
            int res = 0;
            if (len == 3) {
                if (strings[1].equals("����")) {
                    res = 1;
                } else if (strings[1].equals("����")) {
                    res = 2;
                }
            } else if (len == 4) {
                if (strings[1].equals("����")) {
                    if (strings[2].equals("��Ⱦ����")) {
                        res = 3;
                    } else if (strings[2].equals("���ƻ���")) {
                        res = 4;
                    }
                } else if (strings[1].equals("�ų�")) {
                    res = 5;
                } else {
                    res = 6;
                }
            } else {
                if (strings[1].equals("��Ⱦ����")) {
                    res = 7;
                } else {
                    res = 8;
                }
            }
            return res;
        }
        
        /**
         * description������ʡ�ݺͲ�������IDִ����Ӧ�Ĳ���
         * @param province1 ʡ��1
         * @param province2 ʡ��2���п���Ϊ��
         * @param operateType ��������ID��1~8��
         * @param number ִ����Ӧ�޸ĵ� ����
         */
        public static void executeOperate(Province province1, Province province2, int operateType, int number) {
            switch (operateType) {
            case 1:
                province1.increaseDead(number);
                province1.decreaseIp(number);
                break;
            case 2:
                province1.increaseCure(number);
                province1.decreaseIp(number);
                break;
            case 3:
                province1.increaseIp(number);
                break;
            case 4:
                province1.increaseSp(number);
                break;
            case 5:
                province1.decreaseSp(number);
                break;
            case 6:
                province1.decreaseSp(number);
                province1.increaseIp(number);
                break;
            case 7:
                province1.decreaseIp(number);
                province2.increaseIp(number);
                break;
            case 8:
                province1.decreaseSp(number);
                province2.increaseSp(number);
                break;
            default:
                break;
            }
        }

        /**
         * description�����жϸ�����ע���У����ж�ǰ�����ַ�"//"
         * @param string ����һ���ַ���
         * @return ����ֵ
         */
        public static boolean isAnnotation(String lineString) {
            if (lineString.charAt(0) == '/' && lineString.charAt(1) == '/') {
                return true;
            } else {
                return false;
            }
        }

        /**
         * description��ȡ������log����������
         * @param nameStrings ������ļ�������
         * @return �������ڣ����ͣ�Date
         */
        public static Date getMaxDate(String[] nameStrings) {
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            String maxDateString = "2000-01-01";
            Date maxDate = null;
            try {
                maxDate = dFormat.parse(maxDateString);
                for(int i=0; i<nameStrings.length; i++) {
                    Date tmpDate = dFormat.parse(nameStrings[i]);
                    if(tmpDate.getTime() >= maxDate.getTime()) {
                        maxDate = tmpDate;
                    }
                }
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            return maxDate;
        }

        /** description��ȡ�ý�������� */
        public static String getToday() {
            Date todayDate = new Date();
            SimpleDateFormat sdfDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String todayString = sdfDateFormat.format(todayDate);
            return todayString;
        }

        /**
         * description����ȡ�ļ�����ָ������ǰ�������ļ��ļ���
         * @param path �ļ���·��
         * @param date ָ��������
         * @param fileName ��õ��ļ����б�
         */
        public static void getBeforeDateFileName(String path, String date, ArrayList<String> fileName) {
            SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
            File file = new File(path);
            File[] files = file.listFiles();
            String[] nameStrings = file.list();
            Date maxDate = getMaxDate(nameStrings);
            if (nameStrings != null) {
                for (int i = 0; i < nameStrings.length; i++) {
                    String dateOfFileNameString = nameStrings[i].substring(0, nameStrings[i].indexOf('.'));
                    try {
                        Date dateOfFileNameDate = dFormat.parse(dateOfFileNameString);
                        Date limitDate = dFormat.parse(date);
                        if(limitDate.getTime() > maxDate.getTime()) {
                            System.out.println("���ڳ�����Χ");
                        }else {
                            if (dateOfFileNameDate.getTime() <= limitDate.getTime()) {
                                fileName.add(nameStrings[i]);
                            }
                        }
                        
                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }

//                    fileName.addAll(Arrays.asList(nameStrings));
                }
            }
        }

        /**
         * description��ͳ��ʡ������
         * @param lineString һ���ַ���
         * @param hashtable �������ͳ�Ƶ�ʡ��
         */
        public static void calcProvince(String lineString, Hashtable<String, Province> hashtable) {
            InfectStatistic infectStatistic = new InfectStatistic();
            String[] afterSplitStrings = lineString.split(" ");
            int numAfterSplit = afterSplitStrings.length; // �и������
            int number = ToolMethods.getNumber(afterSplitStrings[numAfterSplit - 1]); // һ����Ϣ���漰������
            String[] provinceNameStrings = ToolMethods.getNeedModifyProvinceNames(afterSplitStrings);
            int operateType = ToolMethods.getOperateType(afterSplitStrings);

            if (provinceNameStrings[1].equals("")) { // ֻ��һ��ʡ
                if (!hashtable.containsKey(provinceNameStrings[0])) { // ��ϣ����û�и�ʡ
                    Province province = infectStatistic.new Province(provinceNameStrings[0], 0, 0, 0, 0);
                    ToolMethods.executeOperate(province, province, operateType, number);
                    hashtable.put(province.getProvinceName(), province);
                } else {
                    Province province = hashtable.get(provinceNameStrings[0]);
                    ToolMethods.executeOperate(province, province, operateType, number);
                }
            } else if (!provinceNameStrings[1].equals("")) { // ������ʡ
                Province province1 = null;
                Province province2 = null;
                if (hashtable.containsKey(provinceNameStrings[0]) && hashtable.containsKey(provinceNameStrings[1])) {
                    province1 = hashtable.get(provinceNameStrings[0]);
                    province2 = hashtable.get(provinceNameStrings[1]);
                } else if (hashtable.containsKey(provinceNameStrings[0])
                        && !hashtable.containsKey(provinceNameStrings[1])) {
                    province1 = hashtable.get(provinceNameStrings[0]);
                    province2 = infectStatistic.new Province(provinceNameStrings[1], 0, 0, 0, 0);
                    hashtable.put(provinceNameStrings[1], province2);
                } else if (!hashtable.containsKey(provinceNameStrings[0])
                        && hashtable.containsKey(provinceNameStrings[1])) {
                    province1 = infectStatistic.new Province(provinceNameStrings[0], 0, 0, 0, 0);
                    hashtable.put(provinceNameStrings[0], province1);
                    province2 = hashtable.get(provinceNameStrings[1]);
                } else if (!hashtable.containsKey(provinceNameStrings[0])
                        && !hashtable.containsKey(provinceNameStrings[1])) {
                    province1 = infectStatistic.new Province(provinceNameStrings[0], 0, 0, 0, 0);
                    province2 = infectStatistic.new Province(provinceNameStrings[1], 0, 0, 0, 0);
                    hashtable.put(provinceNameStrings[0], province1);
                    hashtable.put(provinceNameStrings[1], province2);

                }
                ToolMethods.executeOperate(province1, province2, operateType, number);
            }

        }

        /**
         * description��ͳ��ȫ��������
         * @param hashtable ���������в���ͳ�Ƶ�ʡ��
         */
        public static void calcWholeNation(Hashtable<String, Province> hashtable) {
            InfectStatistic infectStatistic = new InfectStatistic();
            Province wholeNation = infectStatistic.new Province("ȫ��", 0, 0, 0, 0);
            Set set = hashtable.keySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) {
                Object keyObject = iterator.next();
                wholeNation.ip += hashtable.get(keyObject).getIp();
                wholeNation.sp += hashtable.get(keyObject).getSp();
                wholeNation.cure += hashtable.get(keyObject).getCure();
                wholeNation.dead += hashtable.get(keyObject).getDead();
            }
            hashtable.put("ȫ��", wholeNation);
        }

    
    }

    public static void main(String[] args) {
        
    }
}
