resource "aws_lb_target_group" "service" {
  name        = local.service_name
  port        = 8080
  protocol    = "HTTP"
  target_type = "ip"
  vpc_id      = var.vpc_id

  health_check {
    path = "/pgr-services/health"
  }

  lifecycle {
    create_before_destroy = true
  }

  tags = {
    Name = local.service_name
  }
}

resource "aws_lb_listener_rule" "service" {
  listener_arn = var.lb_listener_arn

  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.service.arn
  }

  condition {
    path_pattern {
      values = ["/pgr-services/*"]
    }
  }

  tags = {
    Name = local.service_name
  }
}