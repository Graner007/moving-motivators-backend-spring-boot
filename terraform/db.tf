resource "aws_db_instance" "movingmotivators-database" {
  identifier             = "movingmotivators-database"
  name                   = "movingmotivatorsdatabase"
  instance_class         = "db.t3.micro"
  allocated_storage      = 5
  engine                 = "postgres"
  engine_version         = "13.1"
  username               = "mmBoss"
  password               = var.db_password
  db_subnet_group_name   = aws_db_subnet_group.movingmotivators-subnetgroup.name
  vpc_security_group_ids = [aws_security_group.movingmotivators-sg.id]
  publicly_accessible    = true
  skip_final_snapshot    = true

}
