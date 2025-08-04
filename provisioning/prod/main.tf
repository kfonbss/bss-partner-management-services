module "service-resources" {
  source            = "../common"
  env               = var.env
  aws_account_id    = var.aws_account_id
  vpc_id            = var.vpc_id
  lb_listener_arn   = var.listener_arn
  subnet_ids        = var.subnet_ids
  security_group_id = var.security_group_id
  namespace         = var.namespace
  db_instance_class = var.db_instance_class
  performance_insights_enabled = true
  performance_insights_retention_period = 31
  backup_retention_period               = 14
  deletion_protection                   = true
  multi_az                              = true
}
