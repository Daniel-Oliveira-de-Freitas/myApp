<template>
  <div>
    <h2 id="page-heading" data-cy="PostHeading">
      <span v-text="$t('myApp.post.home.title')" id="post-heading">Posts</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('myApp.post.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'PostCreate' }" custom v-slot="{ navigate }">
          <button @click="navigate" id="jh-create-entity" data-cy="entityCreateButton" class="btn btn-primary jh-create-entity create-post">
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('myApp.post.home.createLabel')"> Create a new Post </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && posts && posts.length === 0">
      <span v-text="$t('myApp.post.home.notFound')">No posts found</span>
    </div>
    <div class="table-responsive" v-if="posts && posts.length > 0">
      <table class="table table-striped" aria-describedby="posts">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('myApp.post.title')">Title</span></th>
            <th scope="row"><span v-text="$t('myApp.post.content')">Content</span></th>
            <th scope="row"><span v-text="$t('myApp.post.date')">Date</span></th>
            <th scope="row"><span v-text="$t('myApp.post.blog')">Blog</span></th>
            <th scope="row"><span v-text="$t('myApp.post.tag')">Tag</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="post in posts" :key="post.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PostView', params: { postId: post.id } }">{{ post.id }}</router-link>
            </td>
            <td>{{ post.title }}</td>
            <td>{{ post.content }}</td>
            <td>{{ post.date ? $d(Date.parse(post.date), 'short') : '' }}</td>
            <td>
              <div v-if="post.blog">
                <router-link :to="{ name: 'BlogView', params: { blogId: post.blog.id } }">{{ post.blog.name }}</router-link>
              </div>
            </td>
            <td>
              <span v-for="(tag, i) in post.tags" :key="tag.id"
                >{{ i > 0 ? ', ' : '' }}
                <router-link class="form-control-static" :to="{ name: 'TagView', params: { tagId: tag.id } }">{{ tag.name }}</router-link>
              </span>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PostView', params: { postId: post.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PostEdit', params: { postId: post.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(post)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="myApp.post.delete.question" data-cy="postDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-post-heading" v-text="$t('myApp.post.delete.question', { id: removeId })">
          Are you sure you want to delete this Post?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-post"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removePost()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./post.component.ts"></script>
