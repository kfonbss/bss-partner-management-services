resource "aws_iam_role" "role" {
  name               = local.service_account_role
  assume_role_policy = data.aws_iam_policy_document.assume_role_policy.json
}

data "aws_eks_cluster" "cluster" {
  name = local.cluster_name
}

data "aws_iam_policy_document" "assume_role_policy" {
  statement {
    actions = ["sts:AssumeRoleWithWebIdentity"]

    principals {
      type        = "Federated"
      identifiers = [
        "arn:aws:iam::${var.aws_account_id}:oidc-provider/${replace(data.aws_eks_cluster.cluster.identity[0].oidc[0].issuer, "https://", "")}"
      ]
    }

    condition {
      test     = "StringEquals"
      variable = "${replace(data.aws_eks_cluster.cluster.identity[0].oidc[0].issuer, "https://", "")}:aud"
      values   = ["sts.amazonaws.com"]
    }

    condition {
      test     = "StringEquals"
      variable = "${replace(data.aws_eks_cluster.cluster.identity[0].oidc[0].issuer, "https://", "")}:sub"
      values   = ["system:serviceaccount:${var.namespace}:${var.service_name}"]
    }
  }
}

resource "aws_iam_policy" "sa-policy" {
  name   = local.service_account_policy
  policy = <<EOF
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": [
                "s3:Get*",
                "s3:List*",
                "s3:Put*",
                "s3:DeleteObject",
                "s3:DeleteObjectVersion",
                "s3:PutObject"
            ],
            "Resource": [
                "${aws_s3_bucket.services_bucket.arn}",
                "${aws_s3_bucket.services_bucket.arn}/*"
            ]
        },

        {
          "Effect": "Allow",
          "Action": [
            "rds-db:connect"
          ],
          "Resource": [
            "arn:aws:rds-db:${var.aws_region}:${var.aws_account_id}:dbuser:${module.rds.db_instance_resource_id}/birth_services_user"
          ]
        }
    ]
}
EOF
}


resource "aws_iam_role_policy_attachment" "sa-policy-attachment" {
  role       = aws_iam_role.role.name
  policy_arn = aws_iam_policy.sa-policy.arn
}
