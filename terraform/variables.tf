variable "aws_region" {
    default = "eu-central-1"
}
variable "aws_profile" {
    default = "kisstamas86"
}
variable "project_name" {
    default = "movingmotivators"
}
variable "ec2_type" {
    default = "m5.large"
}

variable "db_password" {
    type        = string
    sensitive   = true
}