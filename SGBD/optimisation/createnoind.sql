Drop TABLE DET;
Drop TABLE PRO;
Drop TABLE COM;
Drop TABLE FOU;
Drop TABLE CLI;

CREATE TABLE CLI(
  NumCli number(5),
  NomCli char(20), 
  Pays char(30), 
  Tel char(15));

CREATE TABLE FOU(
  NumFou number(2),
  NomFou char(20), 
  Pays char(30), 
  Tel char(15));

CREATE TABLE COM(
  NumCom number(5),
  NumCli Number(5),
  FraisPort number(4),
  AnCom number(4));


CREATE TABLE PRO(
  NumPro number(5),
  NumFou number(2),
  NomPro char(20), 
  TypePro char(10),
  PrixUnit number(3));

CREATE TABLE DET(
  NumCom number(5),
  NumPro number(5), 
  Qte number(5), 
  Remise number(5));