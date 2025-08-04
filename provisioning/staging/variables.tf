variable "default_tags" {
  default = {
    Environment = "staging"
    Project     = "ksmart"
    Subproject  = "pgr"
    repo  = "https://github.com/ksmartikm/ksm-pgr-services"
  }
}

variable "region" {
  default = "ap-south-1"
}

variable "env" {
  default = "staging"
}

variable "db_instance_identifier" {
  default = "ksm-fin-services-db-staging-20240828043343817900000001"  
}

variable "aws_account_id" {
  default = "095566081345"
}

variable "vpc_id" {
  default = "vpc-019250b804356e28f"
}

variable "namespace" {
  default = "egov-staging"
}

variable "listener_arn" {
  default = "arn:aws:elasticloadbalancing:ap-south-1:095566081345:listener/app/ksm-lb-staging/c5e8b01460d722c5/c23dc6c59773cbfa"
}

variable "security_group_id" {
  default = "sg-0bdc31ee1101c0d05"
}

variable "subnet_ids" {
  default = [
    "subnet-077ecf3a5789f1568",
    "subnet-0f32204b558a288de",
    "subnet-08fad14023820be7f",
  ]
}
