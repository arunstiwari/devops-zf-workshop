provider "aws" {
  region = "ap-south-1"
}

module "s3backend" {
  source = "git::https://github.com/arunstiwari/terraform-aws-s3backend.git?ref=v0.1.0"
  namespace = "zf-workshop"
}

output "s3backend_config" {
  value = module.s3backend.config
}
