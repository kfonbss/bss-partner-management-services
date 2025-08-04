locals {
  service_name           = join("-", [var.service_name, var.env])
  db_identifier_name     = join("-", [var.service_name, "db", var.env])
  db_name                = join("_", [var.db_name, var.env])
  db_subnet_group_name   = join("-", [var.db_subnet_group_name, var.env])
  service_account_role   = join("-", [var.service_name, "role", var.env])
  service_account_policy = join("-", [var.service_name, "policy", var.env])
  region                 = var.aws_region
  cluster_name           = join("-", [var.cluster_name, var.env])
}
