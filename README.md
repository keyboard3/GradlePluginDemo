# GradlePluginDemo
将gradle plugin项目demo整理

# master分支
- 仅在 build.gradle 可见的插件
- 仅在项目可见的 buildsrc
- 独立项目的 plugin
- debug 调试 plugin
# packageDebug分支
- 在 buildsrc 里调试打包流程
# asm分支
android打包过程提供了Transform api。我们只需要写个gradle plugin 注册Transform类，在这个Transform内部对所有的class做处理<br>
- gradle 修改字节码
为每个 *Activity|*Receiver|!android* 的 on** 回调函数的开始和结束打上Log
<br>
<img src="images/out1.png" width="350">


```java
    @Override
    void visitCode() {
        super.visitCode();
        /* methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out",
                 "Ljava/io/PrintStream;");
         methodVisitor.visitLdcInsn(name + "-before");
         methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println",
                 "(Ljava/lang/String;)V");*/
        methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
        methodVisitor.visitInsn(Opcodes.DUP);
        
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass",
                "()Ljava/lang/Class;");
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName",
                "()Ljava/lang/String;");
        
        methodVisitor.visitLdcInsn(name + "-before")
        methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/keyboard3/gradleplugindemo/MainActivity", "println",
                "(Ljava/lang/String;Ljava/lang/String;)V");
    }
    
    @Override
    void visitInsn(int opcode) {
        if (opcode == Opcodes.RETURN) {
            // mv.visitTypeInsn(Opcodes.NEW, "com/xyz/Check");//新建一个Check类
    
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitInsn(Opcodes.DUP);
    
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Object", "getClass",
                    "()Ljava/lang/Class;");
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Class", "getSimpleName",
                    "()Ljava/lang/String;");
    
            methodVisitor.visitLdcInsn(name + "-after")
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/keyboard3/gradleplugindemo/MainActivity", "println",
                    "(Ljava/lang/String;Ljava/lang/String;)V");
        }
        super.visitInsn(opcode)
    }
```
- 字节码修改之后
```java
public class MainActivity extends AppCompatActivity {
    public MainActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        this.println(this.getClass().getSimpleName(), "onCreate-before");
        super.onCreate(savedInstanceState);
        this.setContentView(2131296283);
        this.println(this.getClass().getSimpleName(), "onCreate-after");
    }

    protected void onResume() {
        this.println(this.getClass().getSimpleName(), "onResume-before");
        super.onResume();
        this.println(this.getClass().getSimpleName(), "onResume-after");
    }

    public void println(String name, String value) {
        System.out.println(name + "-" + value);
    }
}
```
