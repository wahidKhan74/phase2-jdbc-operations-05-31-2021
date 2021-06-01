use mcit_database;

DELIMITER &&
create procedure get_all_products_and_count()
begin 
   select * from e_product ;
   select count(id) as total_products from  e_product;
END &&
DELIMITER ;


use mcit_database;

call get_all_products_and_count();