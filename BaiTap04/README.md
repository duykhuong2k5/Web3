Tạo docker mySQL
docker compose -f mySQL.yaml up -d --build

Truy cập vào database
docker exec -it my-mysql-bt4 mysql -uuser -ppass123 mydb



--Các quyền: admin xem tất cả category và thêm, xóa, sửa
--			 manager xem tất cả category
--           user chỉ xem category và thêm xóa sửa trên user đó



--Set Role id 
UPDATE users SET role_id = 1 WHERE username = 'manager';


