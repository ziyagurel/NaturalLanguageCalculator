import java.util.*;

public class main {
    private static List<String> ulist;
    private static float toplam = 0;
    private static Scanner in;
    private static Map<String, Integer> languageMap;
    public static void main(String[] args) {
        in = new Scanner (System.in);
        languageMap = new HashMap<>();
        languageMap.put("one", 1);
        languageMap.put("two", 2);
        languageMap.put("three", 3);
        languageMap.put("four", 4);
        languageMap.put("five", 5);
        languageMap.put("six", 6);
        languageMap.put("seven", 7);
        languageMap.put("eight", 8);
        languageMap.put("nine", 9);
        languageMap.put("ten", 10);

        System.out.println("Please enter a calculation : ");
        String input= in.nextLine().toLowerCase();

        String calculationArr[]= input.split(" ");
        ulist = new ArrayList<>();
        for (String temp: calculationArr){
            //split process
            ulist.add(temp);
        }
        /**
         * input dizindeki her bir deger karar mekanizmasında işlem önceliğine göre değerlendirilir.
         * Ardından hesaplama işlemi gerçekleştirilir.
         * Öncelikli olarak çarpma ve bölme işlemleri gerçekleştirilir.
         */
        for(int i=0; i<ulist.size(); i++){
            for(int j=0; j<ulist.size(); j++){
                if(ulist.get(j).contains("times") || ulist.get(j).contains("multiplied-by")){
                    if(languageMap.get(ulist.get(j-1)) == null && languageMap.get(ulist.get(j+1)) ==null )
                        toplam = times(Float.parseFloat(ulist.get(j-1)),Float.parseFloat(ulist.get(j+1)));
                    else if(languageMap.get(ulist.get(j-1)) == null){
                        toplam = times(Float.parseFloat(ulist.get(j-1)),languageMap.get(ulist.get(j+1)));}
                    else if(languageMap.get(ulist.get(j+1)) ==null)
                        toplam = times(languageMap.get(ulist.get(j-1)),Float.parseFloat(ulist.get(j+1)));
                    else
                        toplam = times(languageMap.get(ulist.get(j-1)),languageMap.get(ulist.get(j+1)));
                    ulist.remove(j+1);
                    ulist.remove(j);
                    ulist.remove(j-1);
                    if(j<3)
                        fill(String.valueOf(toplam),0);
                    else if(j>=3 && j<ulist.size())
                        fillCenter(String.valueOf(toplam), j-1);
                    else
                        ulist.add(String.valueOf(toplam));

                    write();
                    break;
                }
                if(ulist.get(j).contains("over") || ulist.get(j).contains("divided-by")){
                    if(languageMap.get(ulist.get(j-1)) == null && languageMap.get(ulist.get(j+1)) ==null )
                        toplam = over(Float.parseFloat(ulist.get(j-1)),Float.parseFloat(ulist.get(j+1)));
                    else if(languageMap.get(ulist.get(j-1)) == null)
                        toplam = over(Float.parseFloat(ulist.get(j-1)),languageMap.get(ulist.get(j+1)));
                    else if(languageMap.get(ulist.get(j+1)) ==null)
                        toplam = over(languageMap.get(ulist.get(j-1)),Float.parseFloat(ulist.get(j+1)));
                    else
                        toplam = over(languageMap.get(ulist.get(j-1)),languageMap.get(ulist.get(j+1)));

                    ulist.remove(j+1);
                    ulist.remove(j);
                    ulist.remove(j-1);
                    if(j<3)
                        fill(String.valueOf(toplam),0);
                    else
                        ulist.add(String.valueOf(toplam));

                    write();
                    break;
                }
            }
        }
        System.out.println("plus or minus");
        /**
         * Öncelikli işlemler halledildikten sonra kalan toplama ve çıkartma işlemleri yapılır.
         */
        for(int i=0; i<ulist.size(); i++){
            for(int j=0; j<ulist.size(); j++){
                if(ulist.get(j).contains("minus") || ulist.get(j).contains("subtract") || ulist.get(j).contains("less")){
                    if(languageMap.get(ulist.get(j-1)) == null && languageMap.get(ulist.get(j+1)) ==null )
                        toplam = minus(Float.parseFloat(ulist.get(j-1)),Float.parseFloat(ulist.get(j+1)));
                    else if(languageMap.get(ulist.get(j-1)) == null)
                        toplam = minus(Float.parseFloat(ulist.get(j-1)),languageMap.get(ulist.get(j+1)));
                    else if(languageMap.get(ulist.get(j+1)) ==null)
                        toplam = minus(languageMap.get(ulist.get(j-1)),Float.parseFloat(ulist.get(j+1)));
                    else
                        toplam = minus(languageMap.get(ulist.get(j-1)),languageMap.get(ulist.get(j+1)));
                    ulist.remove(j+1);
                    ulist.remove(j);
                    ulist.remove(j-1);
                    if(j<3)
                        fill(String.valueOf(toplam),0);
                    else
                        ulist.add(String.valueOf(toplam));
                    write();
                    break;
                }
                else if(ulist.get(j).contains("add") || ulist.get(j).contains("plus") ){
                    if(languageMap.get(ulist.get(j-1)) == null && languageMap.get(ulist.get(j+1)) ==null )
                        toplam = plus(Float.parseFloat(ulist.get(j-1)),Float.parseFloat(ulist.get(j+1)));
                    else if(languageMap.get(ulist.get(j-1)) == null)
                        toplam = plus(Float.parseFloat(ulist.get(j-1)),languageMap.get(ulist.get(j+1)));
                    else if(languageMap.get(ulist.get(j+1)) ==null)
                        toplam = plus(languageMap.get(ulist.get(j-1)),Float.parseFloat(ulist.get(j+1)));
                    else
                        toplam = plus(languageMap.get(ulist.get(j-1)),languageMap.get(ulist.get(j+1)));
                    ulist.remove(j+1);
                    ulist.remove(j);
                    ulist.remove(j-1);
                    if(j<3)
                        fill(String.valueOf(toplam),0);
                    else
                        ulist.add(String.valueOf(toplam));
                    write();
                    break;
                }
            }
        }
        System.out.println("\n Toplam: " + toplam);
    }

    private static float over(float sayi1, float sayi2) {
        return sayi1 / sayi2;
    }
    public static float plus(float sayi1, float sayi2){
        return sayi1 + sayi2;
    }
    public static float times(float sayi1, float sayi2){
        return sayi1 * sayi2;
    }
    public static float minus(float sayi1, float sayi2){
        return sayi1 - sayi2;
    }

    /**
     * Write to new values
     */
    public static void write() {
        System.out.println("**New values**");
        System.out.println(ulist);
    }
    /**
     * İki fonksiyonda aynı işi yapmaktadır.
     * Optimize edilecektir.
     * doldur fonk sadece listenin başına eleman eklerken,
     * fillCenter fonk. gelen index değerine göre çalışmaktadır.
     * @param value
     * @param deger
     */
    public static void fill(String value, int deger){
        List<String> ls = new ArrayList<>();
        ls.add(value);
        for (String index : ulist){
            ls.add(index);
        }
        ulist.clear();
        System.out.println("New Values");
        for (String index : ls){
            ulist.add(index);
        }
        System.out.println(ulist);
    }
    public static void fillCenter(String value, int deger){
        List<String> ls = new ArrayList<>();
        for (String index : ulist){
            ls.add(index);
        }
        ulist.clear();
        int z=0;
        for (String index : ls){
            if(z==deger){
                ulist.add(value);
            }
            ulist.add(index);
            z++;
        }
    }
}