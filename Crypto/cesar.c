#include <stdio.h>

/**
*
*Usage xor_cryptkey input_file output_file
*
**/

int main(int argc, char* argv[]){
	FILE *fi, *fo;
	char *cp;
	int c;

	if(cp=argv[1]){
		if((fi=fopen(argv[2], "rb")) != NULL){
			if((fo=fopen(argv[3],"wb")) != NULL){
				while ((c=getc(fi)) !=EOF){
					if(*cp=='\0') cp=argv[1];
					//c =(c+ *(cp++))%'a';
					if(c>='a' && c<='z'){
						c=(c-'a'+*(cp++)-'a')%26 + 'a';
					}
					if(c>='A' && c<='Z'){
						c=(c-'A'+*(cp++)-'a')%26 + 'A';
					}
					if(c>='0' && c<='9'){
                                                c=(c-'0'+*(cp++)-'a')%10 + '0';
                                        }
					putc(c,fo);
				}
				fclose(fo);
			}
			fclose(fi);
		}
	}
	return 1;
}
