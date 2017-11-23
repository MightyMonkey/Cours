#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "crypt.h"
#include "encrypt.h"
#include <math.h>

/**
 *  * chiffrement utilisant le ou exclusif
 *   */
void xor_crypt(char * key, char * texte, char* chiffre){
	char *cp;
	int i;
	char c;

	if((cp=key)){
		for(i=0;i<strlen(texte);i++){
			c=texte[i];
			if(*cp=='\0') cp=key;
			c ^= *(cp++);
			chiffre[i]=c;
		}
	}
}

/**
 *  * dÈchiffrement utilisant le ou exclusif
 *   */
void xor_decrypt(char * key, char * texte, char* chiffre){
	char *cp;
	int i;
	char c;

	if((cp=key)){
		for(i=0;i<strlen(texte);i++){
			c=texte[i];
			if(*cp=='\0') cp=key;
			c ^= *(cp++);
			chiffre[i]=c;
		}
	}
}

/**
 *  * chiffrement utilisant cesar
 *   */
void cesar_crypt(int decallage, char * texte, char* chiffre){
	int i;
	char c;

	if(decallage!=0){
		for(i=0;i<strlen(texte);i++){
			c=texte[i];
			if(c>='a' && c<='z'){
				c=(c-'a'+decallage)%26 + 'a';
			}
			if(c>='A' && c<='Z'){
				c=(c-'A'+decallage)%26 + 'A';
			}
			if(c>='0' && c<='9'){
                c=(c-'0'+decallage)%10 + '0';
            }
			chiffre[i]=c;
		}
	}
	else{
	printf("!!!! Le message n'est pas cryptÈ !!!!\n");
	}
}

/**
 *  * dÈchiffrement utilisant  cesar
 *   */
void cesar_decrypt(int decallage, char * texte, char* chiffre){
        int i;
        char c;

        if(decallage!=0){
            for(i=0;i<strlen(texte);i++){
			c=texte[i];
                if(c>='a' && c<='z'){
                    c=(c-'a'-decallage)%26 + 'a';
                }
                if(c>='A' && c<='Z'){
                    c=(c-'A'-decallage)%26 + 'A';
                }
                if(c>='0' && c<='9'){
                    c=(c-'0'-decallage)%10 + '0';
                }
            chiffre[i]=c;
        	}
        }
        else{
        printf("!!!! Le message n'est pas decrypte !!!!\n");
        }
}

/**
 *  * chiffrement utilisant viginere
 *   */
void viginere_crypt(char * key, char * texte, char* chiffre){
	char *cp;
	int i;
	char c;

	if((cp=key)){
		for(i=0;i<strlen(texte);i++){
			c=texte[i];
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
			chiffre[i]=c;
				
		}
	}
}

/**
 *  * dÈchiffrement utilisant viginere
 *   */
void viginere_decrypt(char * key, char * texte, char* chiffre){
	char *cp;
	int i;
	char c;

	if((cp=key)){
		for(i=0;i<strlen(texte);i++){
			c=texte[i];
					if(*cp=='\0') cp=key;
					//c =(c+ *(cp++))%'a';
					if(c>='a' && c<='z'){
						c=(c-'a'-*(cp++)+'a')%26 + 'a';
					}
					if(c>='A' && c<='Z'){
						c=(c-'A'-*(cp++)+'a')%26 + 'A';
					}
					if(c>='0' && c<='9'){
                        c=(c-'0'-*(cp++)+'a')%10 + '0';
                    }
			chiffre[i]=c;
				
		}
	}
}

/**
 *  * chiffrement utilisant des
 *   */
void des_crypt(char * key, char * texte, char* chiffre, int size)
{
	char * ptrBlocTxt = texte;
	char * ptrBlocChiffre = chiffre;
	int i=0;
	for(i=0;i<size;i++){
		des_encipher((unsigned char*)ptrBlocTxt,(unsigned char*)ptrBlocChiffre,(unsigned char*)key);
		ptrBlocTxt += sizeof(char)*8;
		ptrBlocChiffre += sizeof(char)*8;
	}
}


/**
 *  * dÈchiffrement utilisant des
 *   */
void des_decrypt(char * key, char * texte, char* chiffre, int size)
{
	char * ptrBlocTxt = texte;
	char * ptrBlocChiffre = chiffre;
	int i=0;
	for(i=0;i<size;i++){
		des_decipher((unsigned char*)ptrBlocTxt,(unsigned char*)ptrBlocChiffre,(unsigned char*)key);
		ptrBlocTxt += sizeof(char)*8;
		ptrBlocChiffre += sizeof(char)*8;
		
	}

}

/**
 *  * chiffrement utilisant 3des
 *   */
void tripledes_crypt(char * key1, char * key2, char * texte, char* chiffre,int size)
{
	char * tmp_chiffre;
	char * tmp_texte;
	tmp_texte= (char*)calloc(strlen(texte),sizeof(char));
	tmp_chiffre= (char*)calloc(strlen(texte),sizeof(char));
	des_crypt(key1,texte,tmp_chiffre,size);
	des_decrypt(key2,tmp_chiffre,tmp_texte,size);
	des_crypt(key1,tmp_texte,chiffre,size);
}


/**
 *  * dÈchiffrement utilisant 3des
 *   */
void tripledes_decrypt(char* key1, char* key2, char* texte, char* chiffre, int size)
{
	char * tmp_chiffre;
	char * tmp_texte;
	tmp_texte= (char*)calloc(strlen(texte),sizeof(char));
	tmp_chiffre= (char*)calloc(strlen(texte),sizeof(char));
	des_decrypt(key1,texte,tmp_chiffre,size);
	des_crypt(key2,tmp_chiffre,tmp_texte,size);
	des_decrypt(key1,tmp_texte,chiffre,size);
}


/****************************************************************
 *                                                               *
 *  -------------------------- modexp -------------------------  *
 *                                                               *
 ****************************************************************/

static Huge modexp(Huge a, Huge b, Huge n) {
	
	Huge               y;
	
	/****************************************************************
	 *                                                               *
	 *  Calcule (pow(a, b) % n) avec la mÈthode du carrÈ binaire     *
	 *  et de la multiplication.                                     *
	 *                                                               *
	 ****************************************************************/
	
	y = 1;
	
	while (b != 0) {
		
		/*************************************************************
		 *                                                            *
		 *  Pour chaque 1 de b, on accumule dans y.                   *
		 *                                                            *
		 *************************************************************/
		
		if (b & 1)
			y = (y * a) % n;
		
		/*************************************************************
		 *                                                            *
		 *  …lÈvation de a au carrÈ pour chaque bit de b.             *
		 *                                                            *
		 *************************************************************/
		
		a = (a * a) % n;
		
		/*************************************************************
		 *                                                            *
		 *  On se prÈpare pour le prochain bit de b.                  *
		 *                                                            *
		 *************************************************************/
		
		b = b >> 1;
		
	}
	
	return y;
	
}


/**
 * Transforme une chaine de caractère en chaine d'entier
 */
void texttoint(char * texte, char* chiffre, int size){
	*chiffre='\0';
	int tmp;
	int i;
	for(i=0;i<size;i++){		
	    // on ajoute 10 pour éviter le problème de disparition du 0 devnt les entiers entre 1 et 9 (01 a 09)
		// ceci évite de découper le texte en bloc de taille < n et de les normaliser ensuite
		tmp=(*(texte+i)-'a'+10);
		sprintf(chiffre+strlen(chiffre),"%d%c",tmp,'\0');
	}
}

/**
 * Transforme une chaine d'entier en chaine de caractère
 */ 
void inttotext(char * texte, char* chiffre){
	*chiffre='\0';
	int tmp=0;
	while((*texte) != '\0'){	
	    // lettre de l'alphabet (0..25 correspond pour nous à 10..35)	
		if(10*tmp+(*(texte)-'0') > 36){
		    // on déduit donc 10 pour obtenir la bonne lettre dans l'alphabet
			sprintf(chiffre+strlen(chiffre),"%c%c",tmp+'a'-10, '\0');
			tmp=0;
		}
		tmp=10*tmp+(*(texte)-'0');
		texte++;
	}
}

/**
 * Chiffrement RSA
 */
void rsa_crypt(int e, int n, char * texte, char* chiffre, int size)
{
    int tmp;
	Huge buf=0;
	char* pt;
	char* btmp = (char *)malloc(strlen(texte) * sizeof(char)); 
	
	texttoint(texte,btmp,size);
	pt = btmp;
	*chiffre='\0';
	while((*pt) != '\0'){
		tmp=*pt-'0';
		if((10*buf + tmp) >= n){
		    // on utilise le $ comme séparateur de bloc
			sprintf(chiffre+strlen(chiffre),"%ld$%c",modexp(buf,e,n),'\0');
			buf=0;
		}
		buf=10*buf+tmp;
		pt++;
	}
	sprintf(chiffre+strlen(chiffre),"%ld$%c", modexp(buf,e,n),'\0');
	printf("\n");
}

/**
 * Déchiffrement RSA
 */
void rsa_decrypt(int d, int n, char * texte, char* chiffre)
{
	int tmp;
	char* pt=texte;
	char* tmpc= (char *)malloc(strlen(texte) * sizeof(char)); 
	Huge buf=0;
	
	*tmpc='\0';
	while((*pt) != '\0'){
		// on utilise le $ comme séparateur de bloc
	    	if((*pt) == '$'){
			sprintf(tmpc+strlen(tmpc),"%ld%c",modexp(buf,d,n),'\0');
			buf=0;
		}else{
			tmp=*pt-'0';
			buf=10*buf+tmp;
		}
		pt++;
	}
	sprintf(tmpc+strlen(tmpc),"%ld%c",modexp(buf,d,n),'\0');
	
	inttotext(tmpc,chiffre);
}


