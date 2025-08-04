resource "aws_ecr_repository" "service_name" {
  name = local.service_name
  tags = {
    Name = local.service_name
  }
}

resource "aws_ecr_lifecycle_policy" "purge_policy" {
  repository = aws_ecr_repository.service_name.name
  policy = jsonencode({
    rules = [
      {
        rulePriority = 1
        description  = "Keep last 30 images"
        selection    = {
          tagStatus   = "any"
          countType   = "imageCountMoreThan"
          countNumber = 30
        }
        action = {
          type = "expire"
        }
      }
    ]
  })
}
