JavaOne Tokyo 2012 JVM����BoF �R�[�f�B���O��� ScalaJP
======================================================

��
-----------------------

- Scala 2.9.1
- JavaSE 7u3
- sbt 0.11.2

build ���@
-----------------------

1. [Scala �J�����\�z�菇](https://github.com/scalajp/scalajp.github.com/wiki/scala-develop-environment "Scala �J�����\�z�菇") 
   ���Q�Ƃ��Asbt �C���X�g�[�����s���܂��B
1. �C���X�g�[������ sbt �̎��s�V�F��(Mac, Linux �Ȃ� sbt�AWindows�Ȃ� sbt.bat) ���J���AJVM�I�v�V�������ȉ��ɐݒ肵�܂��B
   `java -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256m`
1. Terminal�A�������̓R�}���h�v�����v�g�ɂāA�v���W�F�N�g���[�g�Ɉړ����A`sbt assembly` �����s���܂��B

���s���@
-----------------------

### Benchmark

1. `java -Xmx1536M -Xss1M -XX:MaxPermSize=256m -cp target/javaone-tokyo-2012-jvm-bof-assembly-0.1.jar org.scala_users.jp.bench.Benchmark`
    - Windows �̏ꍇ�� path ��؂蕶�����o�b�N�X���b�V���ɂ��ĉ������B `target\javaone-tokyo-2012-jvm-bof-assembly-0.1.jar`
    - 32bit OS �̏ꍇ�� `-Xmx1024M` �ɂ��ĉ������B

### DSL

�쐬��

