ui = true

listener "tcp" {
  address     = "0.0.0.0:8200"
  tls_disable = 1
}

storage "postgresql" {
  connection_url = "postgres://developer:password@123@postgres:5432/vault",
  ha_enabled = "true",
  ha_table = "vault_ha_locks"
}

api_addr = "http://127.0.0.1:8200"
