variable "default_tags" {
  default = {
    Environment = "prod"
    Project     = "ksmart"
    Subproject  = "pgr"
    repo  = "https://github.com/ksmartikm/ksm-pgr-services"
  }
}

variable "region" {
  default = "ap-south-1"
}

variable "env" {
  default = "prod"
}

variable "aws_account_id" {
  default = "569197888283"
}

variable "vpc_id" {
  default = "vpc-04fdc05bec82b9cea"
}

variable "namespace" {
  default = "egov"
}

variable "listener_arn" {
  default = "arn:aws:elasticloadbalancing:ap-south-1:569197888283:listener/app/ksm-lb-prod/6bbb54a5dba9ae64/a2d6bff3be41cda2"
}

variable "security_group_id" {
  default = "sg-0614b2feff1234915"
}

variable "subnet_ids" {
  default = [
    "subnet-054d35ce00459ba53",
    "subnet-015b9a89888df461b",
    "subnet-00593dd09a2abd0ea",
  ]
}
  
variable "db_instance_class" {
  default = "db.t4g.large"
}
