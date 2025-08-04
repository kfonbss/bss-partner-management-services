variable "env" {
  type    = string
  default = "dev"
}

variable "aws_region" {
  type    = string
  default = "ap-south-1"
}

variable "service_name" {
  type    = string
  default = "ksm-pgr-services"
}

variable "namespace" {
  default = "egov"
}

variable "aws_account_id" {
}

variable "cluster_name" {
  default = "ksm-eks-cluster"
}

variable "vpc_id" {
}

variable "lb_listener_arn" {
}

variable "db_instance_class" {
  default = "db.t4g.micro"
}

variable "db_allocated_storage" {
  default = 20
}

variable "db_name" {
  default = "pgr"
}

variable "security_group_id" {
  type = string
}

variable "subnet_ids" {
  type = list(string)
}

variable "db_subnet_group_name" {
  default = "ksm-vpc"
}

variable "performance_insights_enabled" {
  default = true
}

variable "performance_insights_retention_period" {
  default = 7
}

variable "backup_retention_period" {
  default = 0
}

variable "multi_az" {
  default = false
}

variable "deletion_protection" {
  default = false
}
