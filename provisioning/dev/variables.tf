variable "default_tags" {
  default = {
    Environment = "dev"
    Project     = "ksmart"
    Subproject  = "pgr"
    repo  = "https://github.com/ksmartikm/ksm-pgr-services"
  }
}

variable "region" {
  default = "ap-south-1"
}

variable "env" {
  default = "dev"
}

variable "aws_account_id" {
  default = "095566081345"
}

variable "vpc_id" {
  default = "vpc-02b88d99c93021a54"
}

variable "listener_arn" {
  default = "arn:aws:elasticloadbalancing:ap-south-1:095566081345:listener/app/ksm-lb-dev/b168644160192e60/e00f935094e059fe"
}

variable "security_group_id" {
  default = "sg-0786bb1cf315f2e7e"
}

variable "subnet_ids" {
  default = [
    "subnet-0a0fd70a9a7f241b1",
    "subnet-0204efe791e76ec3f",
    "subnet-0922045ed43dfa634",
  ]
}
