#include <stdio.h>


//chiffrement utilisant le ou exclusif
void xor_crypt(char* key,char* texte,char* chiffre);

//déchiffrement utilisant le ou exclusif
void xor_decrypt(char* key,char* chiffre, char* clair);

//chiffrement utilisant cesar
void cesar_crypt(int decallage,char* texte, char* chiffre);

//déchiffrement utilisant cesar
void cesar_decrypt(int decallage,char* chiffre,char* clair);

//chiffrement utilisant viginere
void viginere_crypt(char* key,char* texte,char* chiffre);

//déchiffrement utilisant viginere
void viginere_decrypt(char* key,char* chiffre,char* clair);
