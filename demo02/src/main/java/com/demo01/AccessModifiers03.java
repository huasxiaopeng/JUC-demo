package com.demo01;

/**
 * @ClassName AccessModifiers
 * @Description TODO
 * @Author lktbz
 * @Date 2020/5/29
 */
public class AccessModifiers03 {
    static class TestA{
        public void methodPublic(){
            methodPrivate("public");
        }
        protected void  methodProtected(){
             methodPrivate("protected");
        }
        void methodDefault(){
            methodPrivate("default");
        }
        private void methodPrivate(String name){
            System.out.println("我被调用了-->"+name);
        };
    }
    static  class TestB{
        public static void main(String[] args) {
                new TestA().methodPublic();
                new TestA().methodDefault();
                new TestA().methodProtected();
                new TestA().methodPrivate("ss");

        }
        public void methodPublic() {

        }

        protected void methodProtected() {

        }

        void methodDefault() {

        }

        private void methodPrivate() {
        }

    }
}
