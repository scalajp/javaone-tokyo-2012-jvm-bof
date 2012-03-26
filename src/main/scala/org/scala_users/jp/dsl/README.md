# �e�[�}�F�K�w��Java�v���p�e�B�t�@�C�� DSL�쐬

## �y�T�v�z

Groovy, Scala, JRuby�̊e����ŁA���L�ɏq�ׂ�悤�ȊK�w��Java�v���p�e�B�t�@�C��
DSL���쐬���A�����R�[�h�̒����ADSL�Ƃ��Ă̗��֐��Ȃǂ��r����B

Java�̃v���p�e�B�t�@�C���́A

    key=value
    ...

�Ƃ����Akey=value���t���b�g�ɕ��񂾃V���v���Ȃ��̂ŁA�������₷���B�������A���ۂɃv���p�e�B�t�@�C�������p������ʂł́A
key��a.b.c.d�̂悤��.�ŋ�؂�ꂽ�`���ɂȂ��Ă��鎖���قƂ�ǂł���B����.��؂�̌`���́A

    x.y.z.a=
    x.y.z.b=
    x.y.z.c=

�̂悤�ɁA�v���t�B�N�X�̕���(x.y.z)�����ʂł��邱�Ƃ����΂��΂���B���̂悤�ȏꍇ�A���[�U��
�Ӑ}�Ƃ��ẮA�v���p�e�B�̃L�[�ɊK�w�\���������������Ǝv���邪�A�v���p�e�B�t�@�C����
�{���I�Ƀt���b�g�ł��鎖����A���̎����\���ł��Ă��Ȃ��B

�����ŁA��L�Ɠ������̂��K�w�I�ɕ\���ł���DSL����邱�Ƃ��l����B

    compiler.error.message.varNotFound=...
    compiler.error.message.incompatibleType=...
    compiler.error.maxReport=
    compiler.files.input.encoding=
    compiler.files.output.encoding=

�Ƃ����̂��A

    compiler {
     error {
       message {
         varNotFound=
         incompatibleType=
       }
     }
     maxReport=
     files {
       input.encoding=
       output.encoding=
     }
    }

�̂悤�ɋ[���I�ɊK�w�I�ɏ�����ƁA���ǂ݂₷���Ȃ邱�Ƃ��l������B���̂悤�ȁA
�v���p�e�B�t�@�C���̃L�[���K�w�\���ŋL�q�ł���DSL���쐬���鎖�Ƃ���B

## �y�v���z

* ���͌`���͖��Ȃ��B�e�L�X�g�t�@�C���ŏ�L�̂悤�Ȍ`�����������Ă��ǂ����A�����DSL
  �Ƃ��Ď������Ă����܂�Ȃ��B
* �v���p�e�B�t�@�C���̃L�[����.�ŋ�؂�ꂽ������P�ʂƂ��āA�L�[�����K�w�����ĊǗ��ł��鎖
    * x.y.z �Ƃ����L�[�����ȉ��̂悤�ɋL�q�ł���

              x {
                y {
                  z=
                }
              }

* java.util.Properties�I�u�W�F�N�g�֕ϊ����邽�߂̕��@��񋟂��鎖
* Java�`���̃v���p�e�B�t�@�C���ւ̃V���A���C�Y���T�|�[�g���鎖