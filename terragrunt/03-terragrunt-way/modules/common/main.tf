#terraform {
#  backend "s3" {
#    bucket = "zf-workshop-wvk3ft7e2iwz-state-bucket"
#    key = "env/workspace/zf-workshop"
#    region= "ap-south-1"
#    encrypt = true
#    role_arn = "arn:aws:iam::844154758539:role/zf-workshop-wvk3ft7e2iwz-tf-assume-role"
#    dynamodb_table = "zf-workshop-wvk3ft7e2iwz-state-lock"
#  }
#}

variable "region" {
  type = string
}

variable "env_name" {
  type = string
}

provider "aws" {
  region = var.region
}

data "aws_ami" "ubuntu" {
  owners = ["099720109477"]
  most_recent = true
  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*"]
  }
}

resource "aws_instance" "ec2" {
  ami = data.aws_ami.ubuntu.id
  instance_type = "t2.micro"
  tags = {
    Name = var.env_name
  }
}