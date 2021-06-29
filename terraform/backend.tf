terraform {
  backend "s3" {
    bucket  = "movingmotivators"
    key     = "terraform.tfstate"
    region  = "eu-central-1"
    profile = "kisstamas86"
    dynamodb_table = "movingmotivators"
    encrypt = true
  }
}