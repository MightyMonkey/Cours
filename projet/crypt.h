/**
 *  * chiffrement utilisant le ou exclusif
 *   */
void xor_crypt(char* key, char* texte, char* chiffre);

/**
 *  * déchiffrement utilisant le ou exclusif
 *   */
void xor_decrypt(char* key, char* chiffre, char* clair);

/**
 *  * chiffrement utilisant cesar
 *   */
void cesar_crypt(int decallage, char* texte, char* chiffre);

/**
 *  * déchiffrement utilisant  cesar
 *   */
void cesar_decrypt(int decallage, char* chiffre, char* clair);

/**
 *  * chiffrement utilisant vigenere
 *   */
void vigenere_crypt(char* key, char* texte, char* chiffre);

/**
 *  * déchiffrement utilisant vigenere
 *   */
void vigenere_decrypt(char* key, char* chiffre, char* clair);

/**
 *  * chiffrement utilisant des
 *   */
void desECB_crypt(char* key, char* texte, char* chiffre, int size);


/**
 *  * déchiffrement utilisant des
 *   */
void desECB_decrypt(char* key, char* chiffre, char* clair, int size);

/**
 *  * chiffrement utilisant 3des
 *   */
void tripledes_crypt(char* key1, char* key2, char* texte, char* chiffre, int size);


/**
 *  * déchiffrement utilisant 3des
 *   */
void tripledes_decrypt(char* key1, char* key2, char* chiffre, char* clair, int size);

/**
 * Chiffrement RSA
 */
void rsa_crypt(int e, int n, char* texte, char* chiffre, int size);

/**
 * Dechiffrement RSA
 */
void rsa_decrypt(int d, int n, char* chiffre, char* clair);




