remote_state {
  backend = "s3"
  config = {
    bucket = "tekmentors-040402022-zf-workshop"
    key    = "${path_relative_to_include()}/terraform.tfstate"
    region = "ap-south-1"
    encrypt = true
    dynamodb_table = "tekmentors-040402022-zf-workshop-state-lock"
  }
}


locals {
  local_inputs = yamldecode(file("${get_terragrunt_dir()}/inputs.yaml"))
  global_inputs = yamldecode(file("${get_terragrunt_dir()}/inputs.yaml"))
}



inputs = merge(local.global_inputs, local.local_inputs)
