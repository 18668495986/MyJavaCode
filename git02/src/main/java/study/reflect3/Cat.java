package study.reflect3;

/**
 * @version 1.5
 * @author xw
 * @since 2018
 */
@SuppressWarnings("all")
public class Cat {
    private String name="Tom";
    private int id;

    public Cat() {
    }
    public Cat(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * 重写String
     * @return
     */
    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public void call(){ //无参方法
        System.out.println("miao~~~");
    }

    private StringBuffer sum(String word,int num){ //有参方法
        if(num<1) return null;
        StringBuffer buffer = new StringBuffer();
        for(int i=0;i<num;i++){
            buffer.append(word);
        }
        return buffer;
    }
}
