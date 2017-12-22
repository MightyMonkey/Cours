select table_name, column_name, data_type, num_distinct,
 sample_size, to_char(last_analyzed, '     HH24:MI:SS') last_analyzed,
 num_buckets buckets 
from dba_tab_columns 
where table_name in ('CLI','DET', 'FOU', 'COM', 'PRO')
order by table_name, column_id;