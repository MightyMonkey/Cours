select
  ind.table_name,
  ind.uniqueness,
  col.index_name,
  col.column_name,
  ind.distinct_keys,
  ind.sample_size
from
  dba_ind_columns  col,
  dba_indexes      ind
where
  ind.table_owner = 'XDBA'
    and
  ind.table_name in ('CLI','DET', 'COM', 'FOU', 'PRO')
    and
  col.index_owner = ind.owner 
    and
  col.index_name = ind.index_name
    and
  col.table_owner = ind.table_owner
    and
  col.table_name = ind.table_name
order by
  col.table_name,
  col.index_name,
  col.column_position;