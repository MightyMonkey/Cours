#include <stdio.h>
#include "crypto.h"

int main(){

cesar_crypt(4,"texte","result");
system("cat result");
cesar_decrypt(4,"result","result2");


return 1;
}
