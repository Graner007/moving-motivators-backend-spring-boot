terraform {
  backend "s3" {
    bucket  = "moving-motivators"
    key     = "terraform.tfstate"
    region  = "eu-central-1"
    profile = "kisstamas86"
    dynamodb_table = "moving-motivators"
    encrypt = true
  }
}