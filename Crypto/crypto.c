#include "crypto.h"


void xor_crypt(char* key,char* texte,char* chiffre){
	FILE *fi, *fo;
	char *cp;
	int c;

	if(cp=key){
		if((fi=fopen(texte, "rb")) != NULL){
			if((fo=fopen(chiffre,"wb")) != NULL){
				while ((c=getc(fi)) !=EOF){
					if(*cp=='\0') cp=key;
					c ^= *(cp++);
					putc(c,fo);
				}
				fclose(fo);
			}
			fclose(fi);
		}
	}
}

void xor_decrypt(char* key,char* chiffre, char* clair){
	FILE *fi, *fo;
	char *cp;
	int c;

	if(cp=key){
		if((fi=fopen(chiffre, "rb")) != NULL){
			if((fo=fopen(clair,"wb")) != NULL){
				while ((c=getc(fi)) !=EOF){
					if(*cp=='\0') cp=key;
					c ^= *(cp++);
					putc(c,fo);
				}
				fclose(fo);
			}
			fclose(fi);
		}
	}
}

void cesar_crypt(int decallage,char* texte, char* chiffre){
	FILE *fi, *fo;
	int c;

	if(decallage!=0){
		if((fi=fopen(texte, "rb")) != NULL){
			if((fo=fopen(chiffre,"wb")) != NULL){
				while ((c=getc(fi)) !=EOF){
					if(c>='a' && c<='z'){
						c=(c-'a'+decallage)%26 + 'a';
					}
					if(c>='A' && c<='Z'){
						c=(c-'A'+decallage)%26 + 'A';
					}
					if(c>='0' && c<='9'){
                                                c=(c-'0'+decallage)%10 + '0';
                                        }
					putc(c,fo);
				}
				fclose(fo);
			}
			fclose(fi);
		}
	}
	else{
	printf("!!!! Le message n'est pas crypté !!!!\n");
	}
}

void cesar_decrypt(int decallage,char* chiffre, char* clair){
        FILE *fi, *fo;
        int c;

        if(decallage!=0){
                if((fi=fopen(chiffre, "rb")) != NULL){
                        if((fo=fopen(clair,"wb")) != NULL){
                                while ((c=getc(fi)) !=EOF){
                                        if(c>='a' && c<='z'){
                                                c=(c-'a'-decallage)%26 + 'a';
                                        }
                                        if(c>='A' && c<='Z'){
                                                c=(c-'A'-decallage)%26 + 'A';
                                        }
                                        if(c>='0' && c<='9'){
                                                c=(c-'0'-decallage)%10 + '0';
                                        }
                                        putc(c,fo);
                                }
                                fclose(fo);
                        }
                        fclose(fi);
                }
        }
        else{
        printf("!!!! Le message n'est pas decrypte !!!!\n");
        }
}


void viginere_crypt(char* key,char* texte,char* chiffre){
	FILE *fi, *fo;
	char *cp;
	int c;

	if(cp=key){
		if((fi=fopen(texte, "rb")) != NULL){
			if((fo=fopen(chiffre,"wb")) != NULL){
				while ((c=getc(fi)) !=EOF){
					if(*cp=='\0') cp=key;
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
}
