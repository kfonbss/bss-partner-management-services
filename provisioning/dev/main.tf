module "service-resources" {
  source            = "../common"
  env               = var.env
  aws_account_id    = var.aws_account_id
  vpc_id            = var.vpc_id
  lb_listener_arn   = var.listener_arn
  subnet_ids        = var.subnet_ids
  security_group_id = var.security_group_id
}
