prompt 'Un depot de 6000 EUR est fait sur le compte de Joe';
update CLI set solde=solde+6000 where NomCli ='Joe';
prompt 'Nouveau Solde de Joe';
select solde from CLI where NomCli ='Joe';
