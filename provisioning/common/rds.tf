data aws_security_group db_sg {
  id = var.security_group_id
}


module "rds" {
  source  = "terraform-aws-modules/rds/aws"
  version = "6.1.1"

  identifier                     = local.db_identifier_name
  instance_use_identifier_prefix = true

  create_db_option_group    = false
  create_db_parameter_group = false
  create_db_subnet_group    = false

  engine               = "postgres"
  engine_version       = "14"
  family               = "postgres14"
  major_engine_version = "14"
  instance_class       = var.db_instance_class

  allocated_storage = var.db_allocated_storage
  storage_type      = "gp3"

  multi_az                              = var.multi_az
  performance_insights_enabled          = var.performance_insights_enabled
  performance_insights_retention_period = var.performance_insights_retention_period
  deletion_protection                   = var.deletion_protection

  db_name  = local.db_name
  username = "pgrMasterUser"
  port     = 5432

  iam_database_authentication_enabled = true

  vpc_security_group_ids = [data.aws_security_group.db_sg.id]
  db_subnet_group_name   = local.db_subnet_group_name
  subnet_ids             = var.subnet_ids

  maintenance_window      = "Sun:00:00-Sun:03:00"
  backup_window           = "03:00-06:00"
  backup_retention_period = var.backup_retention_period
}
