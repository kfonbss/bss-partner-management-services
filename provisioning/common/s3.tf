resource "aws_s3_bucket" "services_bucket" {
  bucket = var.env == "staging" ? "${local.service_name}-v2" : local.service_name
}

resource "aws_s3_bucket_ownership_controls" "services_bucket_ownership_controls" {
  bucket = aws_s3_bucket.services_bucket.id
  rule {
    object_ownership = "BucketOwnerPreferred"
  }
  }

resource "aws_s3_bucket_acl" "services_bucket_acl" {
  depends_on = [aws_s3_bucket_ownership_controls.services_bucket_ownership_controls]
  bucket     = aws_s3_bucket.services_bucket.id
  acl        = "private"
}

resource "aws_s3_bucket_versioning" "services_bucket_versioning" {
  bucket = aws_s3_bucket.services_bucket.id
  versioning_configuration {
    status = "Enabled"
  }
}